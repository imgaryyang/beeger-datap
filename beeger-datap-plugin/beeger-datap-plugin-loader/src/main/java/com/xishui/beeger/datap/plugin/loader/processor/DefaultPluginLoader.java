package com.xishui.beeger.datap.plugin.loader.processor;

import com.xishui.beeger.datap.plugin.api.starter.ProviderStarter;
import com.xishui.beeger.datap.plugin.loader.PluginLoader;
import com.xishui.beeger.datap.plugin.loader.model.PluginCfg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DefaultPluginLoader implements PluginLoader {
    private final Logger logger = LoggerFactory.getLogger(DefaultPluginLoader.class);

    @Override
    public List<String> loader(List<String> pluginJars) throws Exception {
        final List<String> loaderIds = new ArrayList<>();
        for (int i = 0; i < pluginJars.size(); i++) {
            try {
                doLoader(pluginJars.get(i));
                loaderIds.add(UUID.randomUUID().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (loaderIds.size() <= 0) {
            throw new IllegalStateException("Non-Computer-Plugin Started.");
        }
        return loaderIds;
    }

    @Override
    public String loaderRate(String loaderId) {
        return null;
    }

    private void doLoader(String pluginJar) throws Exception {
        final URL url = builderCfgPath(pluginJar);
        final List<String> clazzNames = builderClazzName(url);
        if (clazzNames.size() <= 0) {
            throw new NullPointerException("ComputePlugin Cant Config MainClass by " + pluginJar);
        }
        if (clazzNames.size() > 1) {
            //todo
            logger.warn("ComputePlugin Cant Config MainClass More than 1");
        }
        startPlugin(pluginJar, clazzNames.get(0));
    }

    private URL builderCfgPath(String pluginJar) throws Exception {
        return new URL("jar:file:" + pluginJar + "!" + PluginCfg.SOURCE_CFG_NAME);
    }

    private URL builderJarPath(String pluginJar) throws Exception {
        return new URL("file:" + pluginJar);
    }

    private List<String> builderClazzName(final URL url) throws Exception {
        InputStream inputStream = null;
        String line = null;
        final List<String> clazzCfg = new ArrayList<>();
        try {
            inputStream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (null != (line = reader.readLine())) {
                if (null == line || "".equals(line.trim())) {
                    break;
                }
                clazzCfg.add(line);
            }
        } catch (Exception e) {
            logger.error("ComputePlugin Load Config File Err.", e);
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }
        }
        return clazzCfg;
    }

    //后续可以加入各种参数
    private void startPlugin(String pluginJar, String mainClazz) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("java -cp ").append(pluginJar).append(" ").append(mainClazz)
//                .append(" >").append("/opt/app/log/beegerdatap/compute/").append(jarName(pluginJar)).append(".log")
                .append(" &");
        logger.info("Command exe:" + builder.toString());
        Process process = Runtime.getRuntime().exec(builder.toString());
        logger.info("Compute Plugin Started by " + pluginJar);
        //add to local cache, to start/stop plugin use todo
    }

    private String jarName(String pluginJar) {
        return pluginJar.substring(pluginJar.lastIndexOf("/") + 1, pluginJar.lastIndexOf("."));
    }

    private void addJarClassPath(String pluginJar) {
        try {
            Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
            add.setAccessible(true);
            add.invoke((URLClassLoader) ClassLoader.getSystemClassLoader(), new Object[]{builderJarPath(pluginJar)});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadClazzAndStart(final List<String> clazzNames, String pluginJar) {
        try {
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{builderJarPath(pluginJar)},
                    Thread.currentThread().getContextClassLoader());
            for (String clazz : clazzNames) {
                Class<?> loadClass = urlClassLoader.loadClass(clazz);
                if (!loadClass.isInterface()
                        && !Modifier.isAbstract(loadClass.getModifiers())
                        && Modifier.isPublic(loadClass.getModifiers())
                        && ProviderStarter.class.isAssignableFrom(loadClass)) {
                    ProviderStarter providerStarter = (ProviderStarter) loadClass.newInstance();
                    //本地模型，启动
                    if (!providerStarter.isRemote()) {
                        providerStarter.startProvider();
                    }
                } else {
                    //todo
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

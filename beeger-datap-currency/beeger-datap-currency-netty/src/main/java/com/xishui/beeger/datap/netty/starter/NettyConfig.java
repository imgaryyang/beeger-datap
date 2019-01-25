package com.xishui.beeger.datap.netty.starter;

import com.xishui.beeger.datap.netty.spi.MessageProcessor;

public final class NettyConfig {

    public static NettyServerConfig createNettyServerConfig() {
        return new NettyServerConfig();
    }

    public static NettyClientConfig createNettyClientConfig() {
        return new NettyClientConfig();
    }

    public static class NettyServerConfig {
        private int exportPort;

        private MessageProcessor messageProcessor;

        public NettyServerConfig exportPort(int exportPort){
            this.exportPort = exportPort;
            return this;
        }
        public NettyServerConfig messageProcessor(MessageProcessor messageProcessor){
            this.messageProcessor  = messageProcessor;
            return this;
        }

        public int getExportPort() {
            return exportPort;
        }

        public void setExportPort(int exportPort) {
            this.exportPort = exportPort;
        }

        public MessageProcessor getMessageProcessor() {
            return messageProcessor;
        }

        public void setMessageProcessor(MessageProcessor messageProcessor) {
            this.messageProcessor = messageProcessor;
        }
    }

    public static class NettyClientConfig {
        private int serverPort;
        private String serverIP;

        public int getServerPort() {
            return serverPort;
        }

        public void setServerPort(int serverPort) {
            this.serverPort = serverPort;
        }

        public String getServerIP() {
            return serverIP;
        }

        public void setServerIP(String serverIP) {
            this.serverIP = serverIP;
        }
    }

}

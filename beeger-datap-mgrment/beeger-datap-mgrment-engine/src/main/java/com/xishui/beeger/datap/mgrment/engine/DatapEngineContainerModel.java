package com.xishui.beeger.datap.mgrment.engine;

import com.xishui.beeger.datap.container.ContainerModel;
import com.xishui.beeger.datap.engine.kylin.driver.KylinDriverChecker;
import com.xishui.beeger.datap.engine.mysql.driver.MysqlDriverChecker;
import com.xishui.beeger.datap.model.DriverClass;
import com.xishui.beeger.datap.model.compute.EngineModel;

public class DatapEngineContainerModel implements ContainerModel {
    @Override
    public void starter() throws RuntimeException {
        /**驱动检查**/
        try {
            // kylin
            new KylinDriverChecker().check(DriverClass.KYLIN.getDriver(), null);
            System.out.println(EngineModel.KYLIN.getModelName() + "  Start Completed");
            //jdbc
            new MysqlDriverChecker().check(DriverClass.MYSQL.getDriver(),null);
            System.out.println(EngineModel.MYSQL.getModelName() + "  Start Completed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() throws RuntimeException {
        /**驱动卸载**/
    }
}

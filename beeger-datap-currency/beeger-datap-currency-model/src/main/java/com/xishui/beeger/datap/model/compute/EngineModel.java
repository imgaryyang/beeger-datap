package com.xishui.beeger.datap.model.compute;

public enum EngineModel {
    PLUGIN("Compute-Engine-Redirect"),
    KYLIN("Compute-Engine-Kylin"),
    MYSQL("Compute-Engine-Mysql"),
    SPARK_SQL("Compute-Engine-Spark_sql"),
    ELASTICSEARCH("Compute-Engine-Elasticsearch"),
    MONGODB("Compute-Engine-Mongodb");

    private String modelName;

    EngineModel(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }

    public static EngineModel matchEngine(String modelName){
        for(EngineModel engineModel : EngineModel.values()){
            if(modelName.equals(engineModel.getModelName())){
                return engineModel;
            }
        }
        return null;
    }
}

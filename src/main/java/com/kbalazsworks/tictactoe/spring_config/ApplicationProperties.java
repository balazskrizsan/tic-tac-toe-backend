package com.kbalazsworks.tictactoe.spring_config;

import org.springframework.beans.factory.annotation.Value;

public class ApplicationProperties
{
    @Value("${server.port}")
    private String serverPort;

    public String getServerPort()
    {
        return serverPort;
    }

    @Value("${logging.file}")
    private String loggingFile;

    public String getLoggingFile()
    {
        return loggingFile;
    }

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    public String getDataSourceDriverClassName()
    {
        return driverClassName;
    }

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    public String getDataSourceUrl()
    {
        return dataSourceUrl;
    }

    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    public String getDataSourceUsername()
    {
        return dataSourceUsername;
    }

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    public String getDataSourcePassword()
    {
        return dataSourcePassword;
    }

    @Value("${env_var_test}")
    private String getEnvVarTest;

    public String getEnvVarTest()
    {
        return getEnvVarTest;
    }

    @Value("${env}")
    private String env;

    public String getEnv()
    {
        return env;
    }
}

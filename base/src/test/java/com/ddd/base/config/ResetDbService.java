package com.ddd.base.config;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Service
public class ResetDbService {

    @Autowired
    private DataSource dataSource;
    private File tempFile;

    public void backup() {
        this.backupCustom();
    }

    public void rollback() {
        reset();
    }

    protected void backupCustom() {
        tempFile = new File("temp.xml");
        IDatabaseConnection connection = null;
        try {
            connection = getConnection();
            QueryDataSet queryDataSet = new QueryDataSet(connection);
            try (FileWriter writer = new FileWriter(tempFile)) {
                FlatXmlDataSet.write(queryDataSet, writer, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    protected IDatabaseConnection getConnection() throws Exception {
        createSchema(dataSource);
        return new DatabaseConnection(DataSourceUtils.getConnection(dataSource));
    }

    protected void reset() {
        IDatabaseConnection connection = null;
        try {
            connection = getConnection();
            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            builder.setColumnSensing(true);
            IDataSet dataSet = builder.build(new FileInputStream(tempFile));
            DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    private void closeConnection(IDatabaseConnection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
        }
    }

    public void createSchema(DataSource dataSource) throws SQLException, IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:/db/*.sql");

        for (Resource resource : resources) {
            String tableName = Objects.requireNonNull(resource.getFilename()).replace(".sql", "");
            if (!tableExists(dataSource, tableName)) {
                ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
                databasePopulator.execute(dataSource);
            }
        }
    }

    private boolean tableExists(DataSource dataSource, String tableName) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData dbMetaData = conn.getMetaData();
            try (ResultSet rs = dbMetaData.getTables(null, null, tableName, null)) {
                return rs.next();
            }
        }
    }

}

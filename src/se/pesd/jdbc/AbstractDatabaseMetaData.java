package se.pesd.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;

public abstract class AbstractDatabaseMetaData implements DatabaseMetaData {

	//protected PrintWriter debug = null;
	protected abstract void debugMsg(String msg);
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		debugMsg("unwrap");
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		debugMsg("isWrapperFor");
		return false;
	}

	@Override
	public boolean allProceduresAreCallable() throws SQLException {
		debugMsg("allProceduresAreCallable");
		return false;
	}

	@Override
	public boolean allTablesAreSelectable() throws SQLException {
		debugMsg("allTablesAreSelectable");
		return false;
	}

	@Override
	public String getURL() throws SQLException {
		debugMsg("getURL");
		return null;
	}

	@Override
	public String getUserName() throws SQLException {
		debugMsg("getUserName");
		return null;
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		debugMsg("isReadOnly");
		return false;
	}

	@Override
	public boolean nullsAreSortedHigh() throws SQLException {
		debugMsg("nullsAreSortedHigh");
		return false;
	}

	@Override
	public boolean nullsAreSortedLow() throws SQLException {
		debugMsg("nullsAreSortedLow");
		return false;
	}

	@Override
	public boolean nullsAreSortedAtStart() throws SQLException {
		debugMsg("nullsAreSortedAtStart");
		return false;
	}

	@Override
	public boolean nullsAreSortedAtEnd() throws SQLException {
		debugMsg("");
		return false;
	}

	@Override
	public String getDatabaseProductName() throws SQLException {
		debugMsg("getDatabaseProductName");
		return "SEDB";
	}

	@Override
	public String getDatabaseProductVersion() throws SQLException {
		debugMsg("getDatabaseProductVersion");
		return "1.0";
	}

	@Override
	public String getDriverName() throws SQLException {
		debugMsg("getDriverName");
		return "se.pesd.jdbc.Driver";
	}

	@Override
	public String getDriverVersion() throws SQLException {
		debugMsg("getDriverVersion");
		return String.format("%d.%d", getDriverMajorVersion(), getDriverMinorVersion());
	}

	@Override
	public int getDriverMajorVersion() {
		debugMsg("getDriverMajorVersion");
		return 1;
	}

	@Override
	public int getDriverMinorVersion() {
		debugMsg("getDriverMinorVersion");
		return 0;
	}

	@Override
	public boolean usesLocalFiles() throws SQLException {
		debugMsg("usesLocalFiles");
		return false;
	}

	@Override
	public boolean usesLocalFilePerTable() throws SQLException {
		debugMsg("usesLocalFilePerTable");
		return false;
	}

	@Override
	public boolean supportsMixedCaseIdentifiers() throws SQLException {
		debugMsg("supportsMixedCaseIdentifiers");
		return false;
	}

	@Override
	public boolean storesUpperCaseIdentifiers() throws SQLException {
		debugMsg("storesUpperCaseIdentifiers");
		return false;
	}

	@Override
	public boolean storesLowerCaseIdentifiers() throws SQLException {
		debugMsg("storesLowerCaseIdentifiers");
		debugMsg("");
		return false;
	}

	@Override
	public boolean storesMixedCaseIdentifiers() throws SQLException {
		debugMsg("storesMixedCaseIdentifiers");
		debugMsg("");
		return false;
	}

	@Override
	public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
		debugMsg("supportsMixedCaseQuotedIdentifiers");
		return false;
	}

	@Override
	public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
		debugMsg("storesUpperCaseQuotedIdentifiers");
debugMsg("");
		return false;
	}

	@Override
	public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
		debugMsg("storesLowerCaseQuotedIdentifiers");
		return false;
	}

	@Override
	public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
		debugMsg("storesMixedCaseQuotedIdentifiers");
		return false;
	}

	@Override
	public String getIdentifierQuoteString() throws SQLException {
		debugMsg("getIdentifierQuoteString");
		return "\"";
	}

	@Override
	public String getSQLKeywords() throws SQLException {
		debugMsg("getSQLKeywords");
		return null;
	}

	@Override
	public String getNumericFunctions() throws SQLException {
		debugMsg("getNumericFunctions");
		return null;
	}

	@Override
	public String getStringFunctions() throws SQLException {
		debugMsg("getStringFunctions");
		return null;
	}

	@Override
	public String getSystemFunctions() throws SQLException {
		debugMsg("getSystemFunctions");
		return null;
	}

	@Override
	public String getTimeDateFunctions() throws SQLException {
		debugMsg("getTimeDateFunctions");
		return null;
	}

	@Override
	public String getSearchStringEscape() throws SQLException {
		debugMsg("getSearchStringEscape");
		return null;
	}

	@Override
	public String getExtraNameCharacters() throws SQLException {
		debugMsg("getExtraNameCharacters");
		return null;
	}

	@Override
	public boolean supportsAlterTableWithAddColumn() throws SQLException {
		debugMsg("supportsAlterTableWithAddColumn");
		return false;
	}

	@Override
	public boolean supportsAlterTableWithDropColumn() throws SQLException {
		debugMsg("supportsAlterTableWithDropColumn");
		return false;
	}

	@Override
	public boolean supportsColumnAliasing() throws SQLException {
		debugMsg("supportsColumnAliasing");
		return false;
	}

	@Override
	public boolean nullPlusNonNullIsNull() throws SQLException {
		debugMsg("nullPlusNonNullIsNull");
		return false;
	}

	@Override
	public boolean supportsConvert() throws SQLException {
		debugMsg("supportsConvert");
		return false;
	}

	@Override
	public boolean supportsConvert(int fromType, int toType) throws SQLException {
		debugMsg("supportsConvert");
		return false;
	}

	@Override
	public boolean supportsTableCorrelationNames() throws SQLException {
		debugMsg("supportsTableCorrelationNames");
		return false;
	}

	@Override
	public boolean supportsDifferentTableCorrelationNames() throws SQLException {
		debugMsg("supportsDifferentTableCorrelationNames");
		return false;
	}

	@Override
	public boolean supportsExpressionsInOrderBy() throws SQLException {
		debugMsg("supportsExpressionsInOrderBy");
		return false;
	}

	@Override
	public boolean supportsOrderByUnrelated() throws SQLException {
		debugMsg("supportsOrderByUnrelated");
		return false;
	}

	@Override
	public boolean supportsGroupBy() throws SQLException {
		debugMsg("supportsGroupBy");
		return false;
	}

	@Override
	public boolean supportsGroupByUnrelated() throws SQLException {
		debugMsg("supportsGroupByUnrelated");
		return false;
	}

	@Override
	public boolean supportsGroupByBeyondSelect() throws SQLException {
		debugMsg("supportsGroupByBeyondSelect");
		return false;
	}

	@Override
	public boolean supportsLikeEscapeClause() throws SQLException {
		debugMsg("supportsLikeEscapeClause");
		return false;
	}

	@Override
	public boolean supportsMultipleResultSets() throws SQLException {
		debugMsg("supportsMultipleResultSets");
		return false;
	}

	@Override
	public boolean supportsMultipleTransactions() throws SQLException {
		debugMsg("supportsMultipleTransactions");
		return false;
	}

	@Override
	public boolean supportsNonNullableColumns() throws SQLException {
		debugMsg("supportsNonNullableColumns");
		return false;
	}

	@Override
	public boolean supportsMinimumSQLGrammar() throws SQLException {
		debugMsg("supportsMinimumSQLGrammar");
		return false;
	}

	@Override
	public boolean supportsCoreSQLGrammar() throws SQLException {
		debugMsg("supportsCoreSQLGrammar");
		return false;
	}

	@Override
	public boolean supportsExtendedSQLGrammar() throws SQLException {
		debugMsg("supportsExtendedSQLGrammar");
		return false;
	}

	@Override
	public boolean supportsANSI92EntryLevelSQL() throws SQLException {
		debugMsg("supportsANSI92EntryLevelSQL");
		return false;
	}

	@Override
	public boolean supportsANSI92IntermediateSQL() throws SQLException {
		debugMsg("supportsANSI92IntermediateSQL");
		return false;
	}

	@Override
	public boolean supportsANSI92FullSQL() throws SQLException {
		debugMsg("supportsANSI92FullSQL");
		return false;
	}

	@Override
	public boolean supportsIntegrityEnhancementFacility() throws SQLException {
		debugMsg("supportsIntegrityEnhancementFacility");
		return false;
	}

	@Override
	public boolean supportsOuterJoins() throws SQLException {
		debugMsg("supportsOuterJoins");
		return false;
	}

	@Override
	public boolean supportsFullOuterJoins() throws SQLException {
		debugMsg("supportsFullOuterJoins");
		return false;
	}

	@Override
	public boolean supportsLimitedOuterJoins() throws SQLException {
		debugMsg("supportsLimitedOuterJoins");
		return false;
	}

	@Override
	public String getSchemaTerm() throws SQLException {
		debugMsg("getSchemaTerm");
		return null;
	}

	@Override
	public String getProcedureTerm() throws SQLException {
		debugMsg("getProcedureTerm");
		return null;
	}

	@Override
	public String getCatalogTerm() throws SQLException {
		debugMsg("getCatalogTerm");
		return null;
	}

	@Override
	public boolean isCatalogAtStart() throws SQLException {
		debugMsg("isCatalogAtStart");
		return false;
	}

	@Override
	public String getCatalogSeparator() throws SQLException {
		debugMsg("getCatalogSeparator");
		return null;
	}

	@Override
	public boolean supportsSchemasInDataManipulation() throws SQLException {
		debugMsg("supportsSchemasInDataManipulation");
		return false;
	}

	@Override
	public boolean supportsSchemasInProcedureCalls() throws SQLException {
		debugMsg("supportsSchemasInProcedureCalls");
		return false;
	}

	@Override
	public boolean supportsSchemasInTableDefinitions() throws SQLException {
		debugMsg("supportsSchemasInTableDefinitions");
		return false;
	}

	@Override
	public boolean supportsSchemasInIndexDefinitions() throws SQLException {
		debugMsg("supportsSchemasInIndexDefinitions");
		return false;
	}

	@Override
	public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
		debugMsg("supportsSchemasInPrivilegeDefinitions");
		return false;
	}

	@Override
	public boolean supportsCatalogsInDataManipulation() throws SQLException {
		debugMsg("supportsCatalogsInDataManipulation");
		return false;
	}

	@Override
	public boolean supportsCatalogsInProcedureCalls() throws SQLException {
		debugMsg("supportsCatalogsInProcedureCalls");
		return false;
	}

	@Override
	public boolean supportsCatalogsInTableDefinitions() throws SQLException {
		debugMsg("supportsCatalogsInTableDefinitions");
		return false;
	}

	@Override
	public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
		debugMsg("supportsCatalogsInIndexDefinitions");
		return false;
	}

	@Override
	public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
		debugMsg("supportsCatalogsInPrivilegeDefinitions");
		return false;
	}

	@Override
	public boolean supportsPositionedDelete() throws SQLException {
		debugMsg("supportsPositionedDelete");
		return false;
	}

	@Override
	public boolean supportsPositionedUpdate() throws SQLException {
		debugMsg("supportsPositionedUpdate");
		return false;
	}

	@Override
	public boolean supportsSelectForUpdate() throws SQLException {
		debugMsg("supportsSelectForUpdate");
		return false;
	}

	@Override
	public boolean supportsStoredProcedures() throws SQLException {
		debugMsg("supportsStoredProcedures");
		return false;
	}

	@Override
	public boolean supportsSubqueriesInComparisons() throws SQLException {
		debugMsg("supportsSubqueriesInComparisons");
		return false;
	}

	@Override
	public boolean supportsSubqueriesInExists() throws SQLException {
		debugMsg("supportsSubqueriesInExists");
		return false;
	}

	@Override
	public boolean supportsSubqueriesInIns() throws SQLException {
		debugMsg("supportsSubqueriesInIns");
		return false;
	}

	@Override
	public boolean supportsSubqueriesInQuantifieds() throws SQLException {
		debugMsg("supportsSubqueriesInQuantifieds");
		return false;
	}

	@Override
	public boolean supportsCorrelatedSubqueries() throws SQLException {
		debugMsg("supportsCorrelatedSubqueries");
		return false;
	}

	@Override
	public boolean supportsUnion() throws SQLException {
		debugMsg("supportsUnion");
		return false;
	}

	@Override
	public boolean supportsUnionAll() throws SQLException {
		debugMsg("supportsUnionAll");
		return false;
	}

	@Override
	public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
		debugMsg("supportsOpenCursorsAcrossCommit");
		return false;
	}

	@Override
	public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
		debugMsg("supportsOpenCursorsAcrossRollback");
		return false;
	}

	@Override
	public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
		debugMsg("supportsOpenStatementsAcrossCommit");
		return false;
	}

	@Override
	public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
		debugMsg("supportsOpenStatementsAcrossRollback");
		return false;
	}

	@Override
	public int getMaxBinaryLiteralLength() throws SQLException {
		debugMsg("getMaxBinaryLiteralLength");
		return 0;
	}

	@Override
	public int getMaxCharLiteralLength() throws SQLException {
		debugMsg("getMaxCharLiteralLength");
		return 0;
	}

	@Override
	public int getMaxColumnNameLength() throws SQLException {
		debugMsg("getMaxColumnNameLength");
		return 0;
	}

	@Override
	public int getMaxColumnsInGroupBy() throws SQLException {
		debugMsg("getMaxColumnsInGroupBy");
		return 0;
	}

	@Override
	public int getMaxColumnsInIndex() throws SQLException {
		debugMsg("getMaxColumnsInIndex");
		return 0;
	}

	@Override
	public int getMaxColumnsInOrderBy() throws SQLException {
		debugMsg("getMaxColumnsInOrderBy");
		return 0;
	}

	@Override
	public int getMaxColumnsInSelect() throws SQLException {
		debugMsg("getMaxColumnsInSelect");
		return 0;
	}

	@Override
	public int getMaxColumnsInTable() throws SQLException {
		debugMsg("getMaxColumnsInTable");
		return 0;
	}

	@Override
	public int getMaxConnections() throws SQLException {
		debugMsg("getMaxConnections");
		return 0;
	}

	@Override
	public int getMaxCursorNameLength() throws SQLException {
		debugMsg("getMaxCursorNameLength");
		return 0;
	}

	@Override
	public int getMaxIndexLength() throws SQLException {
		debugMsg("getMaxIndexLength");
		return 0;
	}

	@Override
	public int getMaxSchemaNameLength() throws SQLException {
		debugMsg("getMaxSchemaNameLength");
		return 0;
	}

	@Override
	public int getMaxProcedureNameLength() throws SQLException {
		debugMsg("getMaxProcedureNameLength");
		return 0;
	}

	@Override
	public int getMaxCatalogNameLength() throws SQLException {
		debugMsg("getMaxCatalogNameLength");
		return 0;
	}

	@Override
	public int getMaxRowSize() throws SQLException {
		debugMsg("getMaxRowSize");
		return 0;
	}

	@Override
	public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
		debugMsg("doesMaxRowSizeIncludeBlobs");
		return false;
	}

	@Override
	public int getMaxStatementLength() throws SQLException {
		debugMsg("getMaxStatementLength");
		return 0;
	}

	@Override
	public int getMaxStatements() throws SQLException {
		debugMsg("getMaxStatements");
		return 0;
	}

	@Override
	public int getMaxTableNameLength() throws SQLException {
		debugMsg("getMaxTableNameLength");
		return 0;
	}

	@Override
	public int getMaxTablesInSelect() throws SQLException {
		debugMsg("getMaxTablesInSelect");
		return 0;
	}

	@Override
	public int getMaxUserNameLength() throws SQLException {
		debugMsg("getMaxUserNameLength");
		return 0;
	}

	@Override
	public int getDefaultTransactionIsolation() throws SQLException {
		debugMsg("getDefaultTransactionIsolation");
		return 0;
	}

	@Override
	public boolean supportsTransactions() throws SQLException {
		debugMsg("supportsTransactions");
		return false;
	}

	@Override
	public boolean supportsTransactionIsolationLevel(int level) throws SQLException {
		debugMsg("supportsTransactionIsolationLevel");
		return false;
	}

	@Override
	public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
		debugMsg("supportsDataDefinitionAndDataManipulationTransactions");
		return false;
	}

	@Override
	public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
		debugMsg("supportsDataManipulationTransactionsOnly");
		return false;
	}

	@Override
	public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
		debugMsg("dataDefinitionCausesTransactionCommit");
		return false;
	}

	@Override
	public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
		debugMsg("dataDefinitionIgnoredInTransactions");
		return false;
	}

	@Override
	public ResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern)
			throws SQLException {
		debugMsg("getProcedures");
		return null;
	}

	@Override
	public ResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern,
			String columnNamePattern) throws SQLException {
		debugMsg("getProcedureColumns");
		return null;
	}

	@Override
	public abstract ResultSet getSchemas() throws SQLException;

	@Override
	public abstract ResultSet getCatalogs() throws SQLException ;

	@Override
	public ResultSet getTableTypes() throws SQLException {
		debugMsg("getTableTypes");
		return null;
	}


	@Override
	public ResultSet getColumnPrivileges(String catalog, String schema, String table, String columnNamePattern)
			throws SQLException {
		debugMsg("getColumnPrivileges");
		return null;
	}

	@Override
	public ResultSet getTablePrivileges(String catalog, String schemaPattern, String tableNamePattern)
			throws SQLException {
		debugMsg("getTablePrivileges");
		return null;
	}

	@Override
	public ResultSet getBestRowIdentifier(String catalog, String schema, String table, int scope, boolean nullable)
			throws SQLException {
		debugMsg("getBestRowIdentifier");
		return null;
	}

	@Override
	public ResultSet getVersionColumns(String catalog, String schema, String table) throws SQLException {
		debugMsg("getVersionColumns");
		return null;
	}

	@Override
	public ResultSet getPrimaryKeys(String catalog, String schema, String table) throws SQLException {
		debugMsg("getPrimaryKeys");
		return null;
	}

	@Override
	public ResultSet getImportedKeys(String catalog, String schema, String table) throws SQLException {
		debugMsg("getImportedKeys");
		return null;
	}

	@Override
	public ResultSet getExportedKeys(String catalog, String schema, String table) throws SQLException {
		debugMsg("getExportedKeys");
		return null;
	}

	@Override
	public ResultSet getCrossReference(String parentCatalog, String parentSchema, String parentTable,
			String foreignCatalog, String foreignSchema, String foreignTable) throws SQLException {
		debugMsg("getCrossReference");
		return null;
	}

	@Override
	public ResultSet getTypeInfo() throws SQLException {
		debugMsg("getTypeInfo");
		return null;
	}

	@Override
	public ResultSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate)
			throws SQLException {
		debugMsg("getIndexInfo");
		return null;
	}

	@Override
	public boolean supportsResultSetType(int type) throws SQLException {
		debugMsg("supportsResultSetType");
		return false;
	}

	@Override
	public boolean supportsResultSetConcurrency(int type, int concurrency) throws SQLException {
		debugMsg("supportsResultSetConcurrency");
		return false;
	}

	@Override
	public boolean ownUpdatesAreVisible(int type) throws SQLException {
		debugMsg("ownUpdatesAreVisible");
		return false;
	}

	@Override
	public boolean ownDeletesAreVisible(int type) throws SQLException {
		debugMsg("ownDeletesAreVisible");
		return false;
	}

	@Override
	public boolean ownInsertsAreVisible(int type) throws SQLException {
		debugMsg("ownInsertsAreVisible");
		return false;
	}

	@Override
	public boolean othersUpdatesAreVisible(int type) throws SQLException {
		debugMsg("othersUpdatesAreVisible");
		return false;
	}

	@Override
	public boolean othersDeletesAreVisible(int type) throws SQLException {
		debugMsg("othersDeletesAreVisible");
		return false;
	}

	@Override
	public boolean othersInsertsAreVisible(int type) throws SQLException {
		debugMsg("othersInsertsAreVisible");
		return false;
	}

	@Override
	public boolean updatesAreDetected(int type) throws SQLException {
		debugMsg("updatesAreDetected");
		return false;
	}

	@Override
	public boolean deletesAreDetected(int type) throws SQLException {
		debugMsg("deletesAreDetected");
		return false;
	}

	@Override
	public boolean insertsAreDetected(int type) throws SQLException {
		debugMsg("insertsAreDetected");
		return false;
	}

	@Override
	public boolean supportsBatchUpdates() throws SQLException {
		debugMsg("supportsBatchUpdates");
		return false;
	}

	@Override
	public ResultSet getUDTs(String catalog, String schemaPattern, String typeNamePattern, int[] types)
			throws SQLException {
		debugMsg("getUDTs");
		return null;
	}

	@Override
	public Connection getConnection() throws SQLException {
		debugMsg("getConnection");
		return null;
	}

	@Override
	public boolean supportsSavepoints() throws SQLException {
		debugMsg("supportsSavepoints");
		return false;
	}

	@Override
	public boolean supportsNamedParameters() throws SQLException {
		debugMsg("supportsNamedParameters");
		return false;
	}

	@Override
	public boolean supportsMultipleOpenResults() throws SQLException {
		debugMsg("supportsMultipleOpenResults");
		return false;
	}

	@Override
	public boolean supportsGetGeneratedKeys() throws SQLException {
		debugMsg("supportsGetGeneratedKeys");
		return false;
	}

	@Override
	public ResultSet getSuperTypes(String catalog, String schemaPattern, String typeNamePattern) throws SQLException {
		debugMsg("getSuperTypes");
		return null;
	}

	@Override
	public ResultSet getSuperTables(String catalog, String schemaPattern, String tableNamePattern) throws SQLException {
		debugMsg("getSuperTables");
		return null;
	}

	@Override
	public ResultSet getAttributes(String catalog, String schemaPattern, String typeNamePattern,
			String attributeNamePattern) throws SQLException {
		debugMsg("getAttributes");
		return null;
	}

	@Override
	public boolean supportsResultSetHoldability(int holdability) throws SQLException {
		debugMsg("supportsResultSetHoldability");
		return false;
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		debugMsg("getResultSetHoldability");
		return 0;
	}

	@Override
	public int getDatabaseMajorVersion() throws SQLException {
		debugMsg("getDatabaseMajorVersion");
		return 0;
	}

	@Override
	public int getDatabaseMinorVersion() throws SQLException {
		debugMsg("getDatabaseMinorVersion");
		return 0;
	}

	@Override
	public int getJDBCMajorVersion() throws SQLException {
		debugMsg("getJDBCMajorVersion");
		return 4;
	}

	@Override
	public int getJDBCMinorVersion() throws SQLException {
		debugMsg("getJDBCMinorVersion");
		return 0;
	}

	@Override
	public int getSQLStateType() throws SQLException {
		debugMsg("getSQLStateType");
		return 0;
	}

	@Override
	public boolean locatorsUpdateCopy() throws SQLException {
		debugMsg("locatorsUpdateCopy");
		return false;
	}

	@Override
	public boolean supportsStatementPooling() throws SQLException {
		debugMsg("supportsStatementPooling");
		return false;
	}

	@Override
	public RowIdLifetime getRowIdLifetime() throws SQLException {
		debugMsg("getRowIdLifetime");
		return null;
	}

	@Override
	public ResultSet getSchemas(String catalog, String schemaPattern) throws SQLException {
		debugMsg("getSchemas");
		return null;
	}

	@Override
	public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
		debugMsg("supportsStoredFunctionsUsingCallSyntax");
		return false;
	}

	@Override
	public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
		debugMsg("autoCommitFailureClosesAllResultSets");
		return false;
	}

	@Override
	public ResultSet getClientInfoProperties() throws SQLException {
		debugMsg("getClientInfoProperties");
		return null;
	}

	@Override
	public ResultSet getFunctions(String catalog, String schemaPattern, String functionNamePattern)
			throws SQLException {
		debugMsg("getFunctions");
		return null;
	}

	@Override
	public ResultSet getFunctionColumns(String catalog, String schemaPattern, String functionNamePattern,
			String columnNamePattern) throws SQLException {
		debugMsg("getFunctionColumns");
		return null;
	}

	@Override
	public ResultSet getPseudoColumns(String catalog, String schemaPattern, String tableNamePattern,
			String columnNamePattern) throws SQLException {
		debugMsg("getPseudoColumns");
		return null;
	}

	@Override
	public boolean generatedKeyAlwaysReturned() throws SQLException {
		debugMsg("generatedKeyAlwaysReturned");
		return false;
	}

}

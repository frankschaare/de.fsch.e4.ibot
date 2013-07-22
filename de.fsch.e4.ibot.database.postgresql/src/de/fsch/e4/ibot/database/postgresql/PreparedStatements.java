package de.fsch.e4.ibot.database.postgresql;

public class PreparedStatements
{
public static final String INSERT_QUOTES = "INSERT INTO quotes(id, isin, typ, date, open, high, low, close, volume, adjclose) VALUES (nextval('quotes_id_seq'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
public static final String SELECT_QUOTES = "SELECT * FROM quotes WHERE isin = ? AND typ = ?";
}

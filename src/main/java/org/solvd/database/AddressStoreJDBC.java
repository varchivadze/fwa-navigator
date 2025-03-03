package org.solvd.database;

import org.solvd.model.Address;
import org.solvd.model.AddressDescription;
import org.solvd.model.IntersectionConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressStoreJDBC implements AddressStore {
    private Connection connection;

    public AddressStoreJDBC() {
        try {
            connection = DriverManager.getConnection("jdbc:xbib:csv:configs");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AddressDescription getById(String addressId) {
        String sql = "SELECT  ID, Unit, Street_Address, City, Country FROM all_nodes WHERE ID = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, addressId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String country = rs.getString("Country");
                String city = rs.getString("City");
                String street = rs.getString("Street_Address");
                String unit = rs.getString("Unit");
                return new AddressDescription(country, city, street, unit);
            }
        } catch (SQLException e) {
            System.err.println("Error executing " + sql + ": " + e);
        }
        return null;
    }

    @Override
    public Address lookup(String country, String city, String street, String unit) {
        String sql = "SELECT  ID, Type, Parent, Unit, Street_Address, City, Country FROM all_nodes WHERE LOWER(Country) = ? AND LOWER(City) = ? AND LOWER(Street_Address) = ? AND LOWER(Unit) = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, country);
            stmt.setString(2, city);
            stmt.setString(3, street);
            stmt.setString(4, unit);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                String type = rs.getString("Type");
                String id = rs.getString("ID");
                String intersectionId = type.equals("Main Node")?id:rs.getString("Parent");
                return new Address(id, intersectionId);
            }
        } catch (SQLException e) {
            System.err.println("Error executing " + sql + ": " + e);
        }
        return null;
    }

    @Override
    public List<String> getValidCountries() {
        String sql = "SELECT DISTINCT Country FROM all_nodes ORDER BY Country ASC";
        List <String> result = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(
                        rs.getString("Country")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error executing " + sql + ": " + e);
        }
        return result;
    }

    @Override
    public List<String> getValidCities(String country) {
        String sql = "SELECT DISTINCT City, Country FROM all_nodes WHERE LOWER(Country) = ? ORDER BY City ASC";
        List <String> result = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, country);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(
                        rs.getString("City")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error executing " + sql + ": " + e);
        }
        return result;
    }

    @Override
    public List<String> getValidStreets(String country, String city) {
        String sql = "SELECT DISTINCT Street_Address, City, Country FROM all_nodes WHERE LOWER(Country) = ? AND LOWER(City) = ? ORDER BY Street_Address ASC";
        List <String> result = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, country);
            stmt.setString(2, city);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(
                        rs.getString("Street_Address")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error executing " + sql + ": " + e);
        }
        return result;
    }

    @Override
    public List<String> getValidUnits(String country, String city, String street) {
        String sql = "SELECT DISTINCT Unit, Street_Address, City, Country FROM all_nodes WHERE LOWER(Country) = ? AND LOWER(City) = ? AND LOWER(Street_Address) = ? ORDER BY Unit ASC";
        List <String> result = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, country);
            stmt.setString(2, city);
            stmt.setString(3, street);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(
                        rs.getString("Unit")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error executing " + sql + ": " + e);
        }
        return result;
    }

    @Override
    public List<String> getAllIntersections() {
        String sql = "SELECT DISTINCT ID, Type FROM all_nodes WHERE Type = ? ORDER BY ID ASC";
        List <String> result = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "Main Node");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(
                        rs.getString("ID")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error executing " + sql + ": " + e);
        }
        return result;
    }

    @Override
    public List<IntersectionConnection> getAllConnections(String transportType) {
        String sql = "SELECT ID, FromId, ToId, Weight, Routes FROM warsaw_edges_" + transportType + " ORDER BY ID ASC";
        List <IntersectionConnection> result = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("ID");
                String from = rs.getString("FromId");
                String to = rs.getString("ToId");
                double weight = rs.getDouble("Weight");
                String routes = rs.getString("Routes");
                IntersectionConnection row = new IntersectionConnection(id, from, to, weight, routes);
                result.add(row);
            }
        } catch (SQLException e) {
            System.err.println("Error executing " + sql + ": " + e);
        }
        return result;
    }
}

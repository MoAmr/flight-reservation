<%@ page language="java" contentType="text/html" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <title>Flights</title>
</head>
<body>
<h2>Flights:</h2>
<table>
    <tr>
        <th>Airlines:</th>
        <th>Departure City:</th>
        <th>Arrival City:</th>
        <th>Departure Time:</th>
    </tr>
    <c:forEach var="flight" items="#{flights}">
        <tr>
            <td>${flight.operatingAirlines}</td>
            <td>${flight.departureCity}</td>
            <td>${flight.arrivalCity}</td>
            <td>${flight.estimatedDepartureTime}</td>
            <td><a href="showCompleteReservation?flightId=${flight.id}">Select</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
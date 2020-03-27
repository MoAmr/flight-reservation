<%@ page language="java" contentType="text/html" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <title>Complete Reservation</title>
</head>
<body>
<h2>Complete Reservation</h2>
Airline: ${flight.operatingAirlines}<br/>
Departure City: ${flight.departureCity}<br/>
Arrival City: ${flight.arrivalCity}<br/>

<form action="completeReservation" method="post">
<pre>
    <h2>Passenger Details:</h2>
    First Name: <input type="text" name="passengerFirstName"/>
    Last Name: <input type="text" name="passengerLastName"/>
    Email: <input type="text" name="passengerEmail"/>
    Phone: <input type="text" name="passengerPhone"/>

    <h2>Card Details:</h2>
    Card Name: <input type="text" name="cardName"/>
    Card Number: <input type="text" name="cardNumber"/>
    Card Expiration Date: <input type="text" name="cardExpirationDate"/>
    Card Security Code: <input type="text" name="cardSecurityCode"/>

    <input type="hidden" name="flightId" value="${flight.id}"/>
    <input type="submit" value="confirm"/>
    </pre>
</form>
</body>
</html>
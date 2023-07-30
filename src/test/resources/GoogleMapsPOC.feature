Feature: Google Maps Proof Of Concept

  Scenario: GMaps search for restaurants nearby
    Given Open Google Maps
    Then Search for Nearby Restaurants
    Then Open only opened restaurants nearby
    And Confirm it is open
    And Get back to Map view

  Scenario: GMaps get distance to Marina beach
    Given Search for Marina Beach
    And Get Directions
    Then Get the Distance
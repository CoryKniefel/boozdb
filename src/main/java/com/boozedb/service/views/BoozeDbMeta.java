package com.boozedb.service.views;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoozeDbMeta {

    final Map<String, List<String>> categories;
    List<Map<String, String>> RUM;
    List<Map<String, String>> CORDIALS;
    List<Map<String, String>> TEQUILA;
    List<Map<String, String>> BRANDY_COGNAC;
    List<Map<String, String>> DOMESTIC_WHISKEY;
    List<Map<String, String>> COCKTAILS;
    List<Map<String, String>> GIN;
    List<Map<String, String>> VODKA;
    List<Map<String, String>> SCOTCH;
    List<Map<String, String>> CANADIAN;
    List<Map<String, String>> IRISH;
    List<Map<String, String>> MEZCAL;
    List<Map<String, String>> OTHER_IMPORTED_WHISKY;
    List<Map<String, String>> VERMOUTH;
    List<Map<String, String>> CACHACA;
    List<Map<String, String>> NEUTRAL_GRAIN_SPIRIT;


    public BoozeDbMeta(Map<String, List<String>> categories)
    {
        this.categories = categories;

        RUM = categories.get("RUM").stream().map(s -> Map.of("value", s)).collect(Collectors.toList());
        TEQUILA = categories.get("TEQUILA").stream().map(s -> Map.of("value", s)).collect(Collectors.toList());
        CORDIALS = categories.get("CORDIALS").stream().map(s -> Map.of("value", s)).collect(Collectors.toList());
        BRANDY_COGNAC = categories.get("BRANDY / COGNAC").stream().map(s -> Map.of("value", s)).collect(Collectors.toList());
        DOMESTIC_WHISKEY = categories.get("DOMESTIC WHISKEY").stream().map(s -> Map.of("value", s)).collect(Collectors.toList());
        COCKTAILS = categories.get("COCKTAILS").stream().map(s -> Map.of("value", s)).collect(Collectors.toList());
        GIN = categories.get("GIN").stream().map(s -> Map.of("value", s)).collect(Collectors.toList());
        VODKA = categories.get("VODKA").stream().map(s -> Map.of("value", s)).collect(Collectors.toList());
        SCOTCH = categories.get("SCOTCH").stream().map(s -> Map.of("value", s)).collect(Collectors.toList());
        CANADIAN = categories.get("CANADIAN").stream().map(s -> Map.of("value", s)).collect(Collectors.toList());
        IRISH = categories.get("IRISH").stream().map(s -> Map.of("value", s)).collect(Collectors.toList());
        MEZCAL = categories.get("MEZCAL").stream().map(s -> Map.of("value", s)).collect(Collectors.toList());
        OTHER_IMPORTED_WHISKY = categories.get("OTHER IMPORTED WHISKY").stream().map(s -> Map.of("value", s)).collect(Collectors.toList());
        VERMOUTH = categories.get("VERMOUTH").stream().map(s -> Map.of("value", s)).collect(Collectors.toList());
        CACHACA = categories.get("CACHACA").stream().map(s -> Map.of("value", s)).collect(Collectors.toList());
        NEUTRAL_GRAIN_SPIRIT = categories.get("NEUTRAL GRAIN SPIRIT").stream().map(s -> Map.of("value", s)).collect(Collectors.toList());
    }
}

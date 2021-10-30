package com.example.util.constants;

public interface JourneyConst {
    String title_validator_regEx = "((?=.*[a-zA-Z])(?!.*[.^$*+?\\[\\]{}|\\\\]).*)";
    String title_search_validator_regEx = "((?!.*[.^$*+?\\[\\]{}|\\\\]).*)";
    Integer title_length_min = 8;
    Integer title_length_max = 64;
}

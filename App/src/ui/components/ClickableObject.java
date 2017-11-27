package ui.components;

import javax.swing.*;

public interface ClickableObject {

    int SAVE_QUESTION = 1;
    int VIEW_QUESTION = 2;
    int ADD_QUESTION = 4;
    int NEXT_QUESTION = 5;
    int ADD_ASSIGNMENT = 6;
    int VIEW_ASSIGNMENTS = 7;
    int DO_ASSIGNMENT = 8;
    int BACK_BUTTON = 9;
    int MULTIPLE_CHOICE_OPTION_1 = 11;
    int MULTIPLE_CHOICE_OPTION_2 = 12;
    int MULTIPLE_CHOICE_OPTION_3 = 13;
    int MULTIPLE_CHOICE_OPTION_4 = 14;
    int MC_BUTTON = 21;
    int SA_BUTTON = 22;
    int CHECKBOX = 31;
    int LABEL = 32;
    int USER_TYPE_1 = 41;
    int USER_TYPE_2 = 42;
    int USER_TYPE_3 = 43;
    int LOGIN_BUTTON = 62;
    int LOGOUT_BUTTON = 63;
    int RAND_OPTION = 65;
    int RMC_BUTTON = 66;
    int RSA_BUTTON = 67;
    int SIMPLE_MATH = 71;
    int STATS1 = 72;
    int STATS2 = 73;

    int[] MULTIPLE_CHOICE_OPTIONS = {MULTIPLE_CHOICE_OPTION_1,
            MULTIPLE_CHOICE_OPTION_2, MULTIPLE_CHOICE_OPTION_3, MULTIPLE_CHOICE_OPTION_4};

    int[] QUESTION_OPTIONS = {MC_BUTTON, SA_BUTTON, RAND_OPTION};

    int[] RAND_OPTIONS = {SIMPLE_MATH, STATS1, STATS2};

    int[] RAND_BUTTONS = {RMC_BUTTON, RSA_BUTTON};

    int[] USER_TYPE_OPTIONS = {USER_TYPE_1, USER_TYPE_2, USER_TYPE_3};


    int getID();
}

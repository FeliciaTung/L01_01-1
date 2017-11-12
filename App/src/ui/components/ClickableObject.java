package ui.components;

import javax.swing.*;

public interface ClickableObject {

    int STUDENT_BUTTON = -10;
    int INSTRUCTOR_BUTTON = -11;
    int SAVE_QUESTION = 1;
    int EDIT_QUESTION = 2;
    int DELETE_QUESTION = 3;
    int ADD_QUESTION = 4;
    int ADD_ASSIGNMENT = 5;
    int VIEW_ASSIGNMENTS = 6;
    int MULTIPLE_CHOICE_OPTION_1 = 11;
    int MULTIPLE_CHOICE_OPTION_2 = 12;
    int MULTIPLE_CHOICE_OPTION_3 = 13;
    int MULTIPLE_CHOICE_OPTION_4 = 14;
    int MC_BUTTON = 21;
    int SA_BUTTON = 22;
    int CHECKBOX = 31;
    int LABEL = 32;
    int BACK_TO_VIEW_ALL_ASSIGN = 52;
    int BACK_TO_VIEW_ASSIGN = 53;
    int BACK_TO_ADD_ASSIGN = 54;
    int NEXT_BUTTON = 61;

    int[] MULTIPLE_CHOICE_OPTIONS = {MULTIPLE_CHOICE_OPTION_1,
            MULTIPLE_CHOICE_OPTION_2, MULTIPLE_CHOICE_OPTION_3, MULTIPLE_CHOICE_OPTION_4};

    int[] QUESTION_OPTIONS = {MC_BUTTON, SA_BUTTON};


    int getID();
}

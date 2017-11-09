package ui.components;

import javax.swing.*;

public interface ClickableObject {

    int SAVE_QUESTION = 1;
    int EDIT_QUESTION = 2;
    int DELETE_QUESTION = 3;
    int MULTIPLE_CHOICE_OPTION_1 = 11;
    int MULTIPLE_CHOICE_OPTION_2 = 12;
    int MULTIPLE_CHOICE_OPTION_3 = 13;
    int MULTIPLE_CHOICE_OPTION_4 = 14;
    int MC_BUTTON = 21;
    int SA_BUTTON = 22;
    int CHECKBOX = 31;
    int NEXT_BUTTON = 51;
    int BACK_BUTTON = 52;

    int[] MULTIPLE_CHOICE_OPTIONS = {MULTIPLE_CHOICE_OPTION_1,
            MULTIPLE_CHOICE_OPTION_2, MULTIPLE_CHOICE_OPTION_3, MULTIPLE_CHOICE_OPTION_4};

    int[] QUESTION_OPTIONS = {MC_BUTTON, SA_BUTTON};


    int getID();
}

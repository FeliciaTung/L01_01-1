package ui;

import javax.swing.*;

interface ClickableObject {

    static final int SAVE_QUESTION = 1;
    static final int MULTIPLE_CHOICE_OPTION_1 = 11;
    static final int MULTIPLE_CHOICE_OPTION_2 = 11;
    static final int MULTIPLE_CHOICE_OPTION_3 = 11;
    static final int MULTIPLE_CHOICE_OPTION_4 = 11;

    int getID();
}

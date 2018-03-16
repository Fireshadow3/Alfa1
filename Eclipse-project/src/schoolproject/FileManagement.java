package schoolproject;

import java.util.ArrayList;

public interface FileManagement {
    ArrayList<String> deleteWordsAfterSlashCharacter();

    ArrayList<String> writeOnFileWordsBetweenThreeAndSevenLetters(String output_file);
}

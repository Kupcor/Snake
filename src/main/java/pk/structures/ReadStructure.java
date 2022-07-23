package pk.structures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadStructure {
    private int verticalLength;
    private int horizontalLength;
    private boolean[][] structureStatuses;

    public ReadStructure(String filePath) throws FileNotFoundException {
        this.readStructure(filePath);
    }

    public void readStructure(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner line = new Scanner(file);
        while (line.hasNextLine()) {
            String arr = line.nextLine();
            if (this.verticalLength == 0) {
                for (int iterator = 0; iterator < arr.length(); iterator++) {
                    this.increaseHorizontalLength();
                }
            }
            this.increaseVerticalLength();
        }

        this.structureStatuses = new boolean[this.verticalLength][this.horizontalLength];

        line = new Scanner(file);
        for (int iterator = 0; iterator < this.verticalLength; iterator++) {
            String arr = line.nextLine();
            for (int subIterator = 0; subIterator < this.horizontalLength; subIterator++) {
                if (arr.charAt(subIterator) == ' ') continue;
                this.setStructurePartsStatus(arr.charAt(subIterator), iterator, subIterator);
            }
        }
    }

    private void setStructurePartsStatus(int value, int yPosition, int xPosition) {
        if (value == '1') this.structureStatuses[yPosition][xPosition] = true;
        else this.structureStatuses[yPosition][xPosition] = false;
    }

    private void increaseVerticalLength() {
        this.verticalLength += 1;
    }

    private void increaseHorizontalLength() {
        this.horizontalLength += 1;
    }

    public int getVerticalLength() {
        return verticalLength;
    }

    public int getHorizontalLength() {
        return horizontalLength;
    }

    public boolean getStructurePartStatus (int yPosition, int xPosition) {
        return structureStatuses[yPosition][xPosition];
    }
}
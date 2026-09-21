package lw01.prelab;

public class ColourPrint extends PrintJob {
    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pages = getPages();
        int printingCost;
        if (pages <= 10) {
            printingCost = pages * 1500;
        } else {
            printingCost = (10 * 1500) + ((pages - 10) * 1000);
        }
        return printingCost + 2000;
    }

    @Override
    public String label() {
        return "Colour";
    }
}
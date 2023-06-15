package muddykat.silmat.auki.utility;

public enum ApplicationMode {
    selection,
    basic,
    trigram;

    public static ApplicationMode selected;

    static {
        ApplicationMode.selected = selection;
    }
}

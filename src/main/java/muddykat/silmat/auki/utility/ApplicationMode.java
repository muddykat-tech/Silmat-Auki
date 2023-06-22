package muddykat.silmat.auki.utility;

public enum ApplicationMode {
    selection,
    trigram, isomorph;

    public static ApplicationMode selected;

    static {
        ApplicationMode.selected = selection;
    }
}

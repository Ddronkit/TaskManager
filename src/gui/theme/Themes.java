package gui.theme;

public enum Themes {
    DEFAULT("/css/default.css"),
    BLUEF("/css/blueFadeTheme.css"),
    ORANB("/css/orangeBlackTheme.css"),
    PINKF("/css/pinkFadeTheme.css"),
    REDB("/css/redBlackTheme.css");

    
    private final String displaytheme;
            
    Themes(String displayTheme){
        this.displaytheme = displayTheme;
    }

    public String getDisplaytheme(){return displaytheme;}
}

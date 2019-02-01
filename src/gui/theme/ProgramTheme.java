package gui.theme;

public class ProgramTheme {
    private Themes theme;

    public ProgramTheme(){
        theme = Themes.PINKF;

    }

    public void changeTheme(Themes newTheme){
        theme = newTheme;
    }

    public Themes getTheme(){
        return theme;
    }



}

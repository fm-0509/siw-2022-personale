package it.uniroma3.siw;

public final class BootstrapAlert {
    private final static String PRIMARY = "primary";
    private final static String SECONDARY = "secondary";
    private final static String SUCCESS = "success";
    private final static String DANGER = "danger";
    private final static String WARNING = "warning";
    private final static String INFO = "info";
    private static final String DEFAULT_TAG = "div";

    private final String message;
    private final String type;
    private final String tag;


    private BootstrapAlert(String message, String type)
    {
        this.type = type;
        this.message = message;
        this.tag = DEFAULT_TAG;
    }

    private BootstrapAlert(String message, String type, String tag)
    {
        this.type = type;
        this.message = message;
        this.tag = tag;
    }

    public String getCSS()
    {
        return "alert-"+this.type;
    }
    public String getMessage()
    {
        return "<" + this.tag + ">" + this.message + "</" + this.tag + ">";
    }


    public static BootstrapAlert Primary(String message)
    {
        return new BootstrapAlert(message, PRIMARY );
    }
    public static BootstrapAlert Primary(String message, String tag)
    {
        return new BootstrapAlert(message, PRIMARY , tag);
    }



    public static BootstrapAlert Secondary(String message)
    {
        return new BootstrapAlert(message, SECONDARY );
    }
    public static BootstrapAlert Secondary(String message, String tag)
    {
        return new BootstrapAlert(message, SECONDARY , tag);
    }

    public static BootstrapAlert Success(String message)
    {
        return new BootstrapAlert(message, SUCCESS );
    }
    public static BootstrapAlert Success(String message, String tag)
    {
        return new BootstrapAlert(message, SUCCESS , tag);
    }

    public static BootstrapAlert Danger(String message)
    {
        return new BootstrapAlert(message, DANGER );
    }
    public static BootstrapAlert Danger(String message, String tag)
    {
        return new BootstrapAlert(message, DANGER , tag);
    }

    public static BootstrapAlert Warning(String message)
    {
        return new BootstrapAlert(message, WARNING );
    }
    public static BootstrapAlert Warning(String message, String tag)
    {
        return new BootstrapAlert(message, WARNING , tag);
    }

    public static BootstrapAlert Info(String message)
    {
        return new BootstrapAlert(message, INFO );
    }
    public static BootstrapAlert Info(String message, String tag)
    {
        return new BootstrapAlert(message, INFO , tag);
    }


    public String getHTML()
    {
        return          "<div class=\"alert alert-dismissible fade show "+ this.getCSS() + "\"  role=\"alert\">\n" +
                        this.getMessage() +"\n" +
                        "    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\" />\n" +
                        "  </div>";
    }


}

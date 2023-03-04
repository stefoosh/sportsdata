package sh.stefoosh.sportsdata.constants;

public final class Package {
    public static final boolean ON_LOCAL_MAC = "Mac OS X".equals(java.lang.System.getProperty("os.name"));
    public static final String SH_STEFOOSH_SPORTSDATA = "sh.stefoosh.sportsdata";
    public static final String SH_STEFOOSH_SPORTSDATA_WEB = SH_STEFOOSH_SPORTSDATA + ".application";
    public static final String SH_STEFOOSH_SPORTSDATA_REPOSITORY = SH_STEFOOSH_SPORTSDATA + ".repository";
    public static final String SH_STEFOOSH_SPORTSDATA_CONSTANTS = SH_STEFOOSH_SPORTSDATA + ".constants";
    public static final String SH_STEFOOSH_SPORTSDATA_MODEL = SH_STEFOOSH_SPORTSDATA + ".model";

    public static final String CLASS_CANNOT_BE_INSTANTIATED = "%s is a utility class and cannot be instantiated";

    private Package() {
        throw new UnsupportedOperationException(
                String.format(CLASS_CANNOT_BE_INSTANTIATED, this.getClass().getName()));
    }
}

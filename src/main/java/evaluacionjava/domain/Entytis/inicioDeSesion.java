package evaluacionjava.domain.Entytis;

public class inicioDeSesion {
    private static int currentUserId = -1;  

    public static int getCurrentUserId() {
        return currentUserId;
    }

    public static void setCurrentUserId(int userId) {
        currentUserId = userId;
    }

    public static void clearSession() {
        currentUserId = -1;
    }
}


public interface UserRepository {


    boolean isUserExists(String login);

    void saveUser(User user);

    User getUserByUsername(String login);
}

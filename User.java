import java.util.ArrayList;
import java.util.List;

public class User {
    private String userID;
    private List<User> followers;
    private List<User> followings;
    private List<String> tweets;

    public User(String userID) {
        this.userID = userID;
        this.followers = new ArrayList<>();
        this.followings = new ArrayList<>();
        this.tweets = new ArrayList<>();
    }

    public String getUserID() {
        return userID;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public List<User> getFollowings() {
        return followings;
    }

    public List<String> getTweets() {
        return tweets;
    }

    public void addFollower(User follower) {
        followers.add(follower);
    }

    public void addFollowing(User following) {
        followings.add(following);
    }

    public void postTweet(String tweet) {
        tweets.add(tweet);
        notifyFollowers();
    }

    private void notifyFollowers() {
        for (User follower : followers) {
            follower.update();
        }
    }

    private void update() {
        // This method can be extended if necessary when a follower is notified.
        // For example, you might want to update a UI component.
    }
}

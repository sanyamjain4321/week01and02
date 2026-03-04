import java.util.*;

public class week01and02 {

    public static void main(String[] args) {

        RateLimiter limiter = new RateLimiter();

        System.out.println(limiter.checkRateLimit("client1"));
        System.out.println(limiter.checkRateLimit("client1"));
    }
}

class TokenBucket {

    int tokens = 1000;
    long last = System.currentTimeMillis();

    synchronized boolean allow() {

        long now = System.currentTimeMillis();

        if (now - last > 3600000) {
            tokens = 1000;
            last = now;
        }

        if (tokens > 0) {
            tokens--;
            return true;
        }

        return false;
    }
}

class RateLimiter {

    Map<String, TokenBucket> clients = new HashMap<>();

    public boolean checkRateLimit(String client) {

        clients.putIfAbsent(client, new TokenBucket());

        return clients.get(client).allow();
    }
}
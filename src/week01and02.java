import java.util.*;

public class week01and02 {

    public static void main(String[] args) {

        DNSCache cache = new DNSCache();

        System.out.println(cache.resolve("Google.com"));
        System.out.println(cache.resolve("Google.com"));
    }
}

class DNSEntry {

    String ip;
    long expiry;

    DNSEntry(String ip, int ttl) {
        this.ip = ip;
        this.expiry = System.currentTimeMillis() + ttl * 1000;
    }

    boolean expired() {
        return System.currentTimeMillis() > expiry;
    }
}

class DNSCache {

    private Map<String, DNSEntry> cache = new HashMap<>();

    public String resolve(String domain) {

        if (cache.containsKey(domain)) {

            DNSEntry entry = cache.get(domain);

            if (!entry.expired()) {
                return entry.ip;
            }

            cache.remove(domain);
        }

        String ip = "172.217.14." + new Random().nextInt(255);

        cache.put(domain, new DNSEntry(ip, 300));

        return ip;
    }
}
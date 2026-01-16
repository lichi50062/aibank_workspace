package com.ibm.tw.servers.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.util.EncodingUtils;

public class OauthPasswordEncoder implements PasswordEncoder {
    private static final int DEFAULT_ITERATIONS = 1024;

    private final Digester digester;

    private final byte[] secret;

    private final BytesKeyGenerator saltGenerator;

    /**
     * Constructs a standard password encoder with no additional secret value.
     */
    public OauthPasswordEncoder() {
        this("");
    }

    /**
     * Constructs a standard password encoder with a secret value which is also included in the password hash.
     * 
     * @param secret
     *            the secret key used in the encoding process (should not be shared)
     */
    public OauthPasswordEncoder(CharSequence secret) {
        this("SHA-256", secret);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return encode(rawPassword, this.saltGenerator.generateKey());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        byte[] digested = decode(encodedPassword);
        byte[] salt = EncodingUtils.subArray(digested, 0, this.saltGenerator.getKeyLength());
        return MessageDigest.isEqual(digested, digest(rawPassword, salt));
    }

    private OauthPasswordEncoder(String algorithm, CharSequence secret) {
        this.digester = new Digester(algorithm, DEFAULT_ITERATIONS);
        this.secret = Utf8.encode(secret);
        this.saltGenerator = KeyGenerators.secureRandom();
    }

    private String encode(CharSequence rawPassword, byte[] salt) {
        byte[] digest = digest(rawPassword, salt);
        return new String(Hex.encode(digest));
    }

    private byte[] digest(CharSequence rawPassword, byte[] salt) {
        byte[] digest = this.digester.digest(EncodingUtils.concatenate(salt, this.secret, Utf8.encode(rawPassword)));
        return EncodingUtils.concatenate(salt, digest);
    }

    private byte[] decode(CharSequence encodedPassword) {
        return Hex.decode(encodedPassword);
    }

    public class Digester {

        private final String algorithm;

        private int iterations;

        /**
         * Create a new Digester.
         * 
         * @param algorithm
         *            the digest algorithm; for example, "SHA-1" or "SHA-256".
         * @param iterations
         *            the number of times to apply the digest algorithm to the input
         */
        Digester(String algorithm, int iterations) {
            // eagerly validate the algorithm
            createDigest(algorithm);
            this.algorithm = algorithm;
            setIterations(iterations);
        }

        byte[] digest(byte[] value) {
            MessageDigest messageDigest = createDigest(this.algorithm);
            for (int i = 0; i < this.iterations; i++) {
                value = messageDigest.digest(value);
            }
            return value;
        }

        void setIterations(int iterations) {
            if (iterations <= 0) {
                throw new IllegalArgumentException("Iterations value must be greater than zero");
            }
            this.iterations = iterations;
        }

        private static MessageDigest createDigest(String algorithm) {
            try {
                return MessageDigest.getInstance(algorithm);
            }
            catch (NoSuchAlgorithmException ex) {
                throw new IllegalStateException("No such hashing algorithm", ex);
            }
        }

    }
}
package com.example.bananaandroid;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServerConnection {
    private String serverURL;
    private String name;

    private ServerConnection() {
        name = null;
        serverURL = "http://10.0.2.2:8080";
    }


    private static ServerConnection instance = null;
    public static ServerConnection getInstance() {
        if (instance == null) {
            instance = new ServerConnection();
        }
        return instance;
    }


    public boolean isConnected() {
        return name != null;
    }

    public boolean connect(String name) {
        AtomicBoolean success = new AtomicBoolean(false);

        Thread thread = new Thread(() -> {
            HttpURLConnection httpConnection = null;
            try {
                String charset = "UTF-8";
                String query = String.format("name=%s", URLEncoder.encode(name, charset));
                httpConnection = (HttpURLConnection) new URL(serverURL + "/connect?" + query).openConnection();

                if (httpConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new RuntimeException();
                } else {
                    success.set(true);
                }
            } catch (Exception e) {
                // TODO: CATCH
            } finally {
                if (httpConnection != null) {
                    httpConnection.disconnect();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO: CATCH
        } finally {
            if (success.get()) {
                this.name = name;
            }
        }

        return success.get();
    }


    public boolean register(String name) {
        AtomicBoolean success = new AtomicBoolean(false);

        Thread thread = new Thread(() -> {
            HttpURLConnection httpConnection = null;
            try {
                String charset = "UTF-8";
                String query = String.format("name=%s", URLEncoder.encode(name, charset));
                httpConnection = (HttpURLConnection) new URL(serverURL + "/register?" + query).openConnection();

                if (httpConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new RuntimeException();
                } else {
                    success.set(true);
                }
            } catch (Exception e) {
                // TODO: CATCH
            } finally {
                if (httpConnection != null) {
                    httpConnection.disconnect();
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO: CATCH
        }

        return success.get();
    }

    public boolean get_bananas(int ammount) {
        AtomicBoolean success = new AtomicBoolean(false);

        Thread thread = new Thread(() -> {
            HttpURLConnection httpConnection = null;
            try {
                String charset = "UTF-8";
                String query = String.format("name=%s&num=%d", URLEncoder.encode(name, charset), ammount);
                java.net.URLConnection connection = new URL(serverURL + "/get_bananas?" + query).openConnection();

                if (httpConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new RuntimeException();
                } else {
                    success.set(true);
                }
            } catch (Exception e) {
                // TODO: CATCH
            } finally {
                if (httpConnection != null) {
                    httpConnection.disconnect();
                }
            }
        });

        thread.start();
        try {
        thread.join();
        } catch (InterruptedException e) {
            // TODO: CATCH
        }

        return success.get();
    }


    public boolean send_bananas(int ammount, String receiverName) {
        AtomicBoolean success = new AtomicBoolean(false);

        Thread thread = new Thread(() -> {
            HttpURLConnection httpConnection = null;
            try {
                String charset = "UTF-8";
                String query = String.format("name1=%s&name2=%s&num=%d", URLEncoder.encode(name, charset), URLEncoder.encode(receiverName, charset), ammount);
                java.net.URLConnection connection = new URL(serverURL + "/send_bananas?" + query).openConnection();

                if (httpConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new RuntimeException();
                } else {
                    success.set(true);
                }
            } catch (Exception e) {
                // TODO: CATCH
            } finally {
                if (httpConnection != null) {
                    httpConnection.disconnect();
                }
            }
        });

        thread.start();
        try {
        thread.join();
        } catch (InterruptedException e) {
            // TODO: CATCH
        }

        return success.get();
    }

}

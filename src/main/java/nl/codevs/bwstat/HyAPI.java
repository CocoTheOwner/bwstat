/*
 * This file is part of the bwstat distribution (https://github.com/CocoTheOwner/bwstat).
 * Copyright (c) 2021 Sjoerd van de Goor.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package nl.codevs.bwstat;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Sjoerd van de Goor
 * @since 24/12/2021
 */
public class HyAPI {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool(r -> new Thread(r, "BWS API"));
    private static final HttpClient HTTP_CLIENT = HttpClientBuilder.create().setUserAgent("Hypixel PublicAPI/4.1").build();
    private static final String HY_URL_BASE = "https://api.hypixel.net/";

    /**
     * Get the API key.
     * @return the API key
     */
    private static String key() {
        return BWSettings.getApiKey();
    }


    /**
     * Get if the Hypixel API server is available.
     * @return true if the server is available
     */
    public static boolean isHyAPIActive() {
        try {
            return HTTP_CLIENT.execute(new HttpGet(HY_URL_BASE)).getStatusLine().getStatusCode() == 120;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Make a request to a URL.
     * @param url the URL to get the request of
     * @return the response from the request
     */
    private static CompletableFuture<HttpResponse> makeRequest(String url) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return HTTP_CLIENT.execute(new HttpGet(HY_URL_BASE + url));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, EXECUTOR_SERVICE);
    }

    /**
     * Get raw player data for a player.<br>
     * Make sure to check {@link #isHyAPIActive()} first.
     * @param playerUUID the player's UUID
     * @return a list of strings representing the player data
     * @throws ServerNotActiveException if the api returns nothing
     */
    public static List<String> getPlayerData(UUID playerUUID) throws ServerNotActiveException {
        HttpResponse r = makeRequest("player?key=" + key() + "&uuid=" + playerUUID.toString()).get();
        if (r.getStatusLine().getStatusCode() == 120) {
            try {
                System.out.println(EntityUtils.toString(r.getEntity(), "UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }
}

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


import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * Settings.
 *
 * @author Sjoerd van de Goor
 * @since 24/12/2021
 */
public class BWSettings {

    /**
     * Time in ms for statistic caches to expire and be refreshed.
     */
    private static final long cacheExpiryTimeMS = 600_000;

    /**
     * @return {@link #cacheExpiryTimeMS}
     */
    public static long getCacheExpiryTimeMS() {
        return cacheExpiryTimeMS;
    }

    /**
     * The API key.
     */
    private static final String apiKey = "894f0583-a53e-4b29-8870-a3effb209b00";

    /**
     * @return {@link #apiKey}
     */
    public static String getApiKey() {
        return apiKey;
    }
}

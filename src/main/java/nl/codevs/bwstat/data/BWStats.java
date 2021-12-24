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

package nl.codevs.bwstat.data;

import nl.codevs.bwstat.HyAPI;

import java.rmi.server.ServerNotActiveException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static nl.codevs.bwstat.data.BWStatType.*;

/**
 * Bedwars statistics (derived from Hypixel API response)
 *
 * @author Sjoerd van de Goor
 * @since 24/12/2021
 */
public class BWStats extends HashMap<BWStatType, Integer> {

    /**
     * Create statistics from a player's UUID.
     * @param playerUUID the UUID
     */
    public BWStats(UUID playerUUID) throws ServerNotActiveException, InvalidParameterException {
        if (!HyAPI.isHyAPIActive()) {
            throw new ServerNotActiveException("Hypixel API server not reachable");
        }
        List<String> apiData = HyAPI.getPlayerData(playerUUID);
    }


    /**
     * @return final-kills/final-deaths
     */
    public float getFinalKillDeathRatio() {
        return (float) get(finalKills)/get(finalDeaths);
    }


    /**
     * @return finals/game
     */
    public float getFinalsPerGame() {
        return (float) get(finalKills)/get(games);
    }

    /**
     * @return wins/losses
     */
    public float getWinLossRatio() {
        return (float) get(wins)/get(losses);
    }

    /**
     * @return kills/deaths
     */
    public float getKillDeathRatio() {
        return (float) get(kills)/get(deaths);
    }

    /**
     * Get index score.
     * @return F-KDR^2 * level / 10
     */
    public float getIndexScore() {
        return (float) Math.pow(getFinalKillDeathRatio(), 2) * get(level) / 10;
    }

}

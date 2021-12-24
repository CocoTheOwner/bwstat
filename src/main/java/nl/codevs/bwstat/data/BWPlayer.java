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

import net.minecraft.entity.player.EntityPlayer;
import nl.codevs.bwstat.BWSettings;

/**
 * Bedwars Player
 *
 * @author Sjoerd van de Goor
 * @since 24/12/2021
 */
public class BWPlayer {

    /**
     * The player.
     */
    private final EntityPlayer player;

    /**
     * The stats.
     */
    private BWStats stats;

    /**
     * The last time this player was updated (in MS)
     */
    private long lastUpdated;

    /**
     * Construct a new player with stats.
     * @param entityPlayer the player
     */
    public BWPlayer(EntityPlayer entityPlayer) {
        player = entityPlayer;

        // Get stats
        updateStats();
    }

    /**
     * Get stats.
     * @return the stats
     */
    public BWStats getStats() {
        return stats;
    }

    /**
     * Get player.
     * @return the player
     */
    public EntityPlayer getPlayer() {
        return player;
    }

    /**
     * Update player statistics (checks for cache expiry).
     */
    public void updateStats() {
        if (lastUpdated + BWSettings.getCacheExpiryTimeMS() > System.currentTimeMillis()) {
            return;
        }

        // Reset update time
        lastUpdated = System.currentTimeMillis();

        // TODO: Set stats

    }
}

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

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * All players in the lobby managed in one place
 *
 * @author Sjoerd van de Goor
 * @since 24/12/2021
 */
public class BWLobby extends ConcurrentHashMap<UUID, BWPlayer> {

    /**
     * Update player statistics with a list of player entities.
     * @param playerList the players
     */
    public void update(List<EntityPlayer> playerList) {
        playerList.forEach(p -> {
            if (contains(p.getUniqueID())) {
                get(p.getUniqueID()).updateStats();
            } else {
                put(p.getUniqueID(), new BWPlayer(p));
            }
        });
    }
}

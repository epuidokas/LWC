/**
 * This file is part of LWC (https://github.com/Hidendra/LWC)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.griefcraft.integration.currency;

import com.griefcraft.integration.ICurrency;
import cosine.boseconomy.BOSEconomy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BOSECurrency implements ICurrency {

    /**
     * The BOSEconomy plugin instance
     */
    private BOSEconomy handler;

    public BOSECurrency() {
        handler = (BOSEconomy) Bukkit.getServer().getPluginManager().getPlugin("BOSEconomy");
    }

    public boolean isActive() {
        return true;
    }

    public String format(double money) {
        return handler.getMoneyFormatted(money);
    }

    public String getMoneyName() {
        return handler.getMoneyName();
    }

    public double getBalance(Player player) {
        return handler.getPlayerMoneyDouble(player.getName());
    }

    public boolean canAfford(Player player, double money) {
        return getBalance(player) >= money;
    }

    public double addMoney(Player player, double money) {
        handler.addPlayerMoney(player.getName(), money, false);

        return getBalance(player);
    }

    public double removeMoney(Player player, double money) {
        // we're removing money, so it should be positive
        if (money > 0) {
            money = -money;
        }

        handler.addPlayerMoney(player.getName(), money, false);

        return getBalance(player);
    }

}

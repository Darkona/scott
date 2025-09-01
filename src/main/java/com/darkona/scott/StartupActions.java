package com.darkona.scott;

import io.github.darkona.logged.Logged;
import io.github.darkona.logged.api.LogDecorator;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static io.github.darkona.logged.utils.Colorizer.colorizeHex;

@Component

public class StartupActions {

    private final DemoService demoService;

    private static final Logger log = LoggerFactory.getLogger(StartupActions.class);
    private final LogDecorator deco;

    private final List<Map.Entry<String, String>> kanto151 = List.of(
            Map.entry("1", colorizeHex("#78C850", "Bulbasaur")),
            Map.entry("2", colorizeHex("#78C850", "Ivysaur")),
            Map.entry("3", colorizeHex("#78C850", "Venusaur")),
            Map.entry("4", colorizeHex("#EE8130", "Charmander")),
            Map.entry("5", colorizeHex("#EE8130", "Charmeleon")),
            Map.entry("6", colorizeHex("#EE8130", "Charizard")),
            Map.entry("7", colorizeHex("#6890F0", "Squirtle")),
            Map.entry("8", colorizeHex("#6890F0", "Wartortle")),
            Map.entry("9", colorizeHex("#6890F0", "Blastoise")),
            Map.entry("10", colorizeHex("#A8B820", "Caterpie")),
            Map.entry("11", colorizeHex("#A8B820", "Metapod")),
            Map.entry("12", colorizeHex("#A8B820", "Butterfree")),
            Map.entry("13", colorizeHex("#A8B820", "Weedle")),
            Map.entry("14", colorizeHex("#A8B820", "Kakuna")),
            Map.entry("15", colorizeHex("#A8B820", "Beedrill")),
            Map.entry("16", colorizeHex("#A8A878", "Pidgey")),
            Map.entry("17", colorizeHex("#A8A878", "Pidgeotto")),
            Map.entry("18", colorizeHex("#A8A878", "Pidgeot")),
            Map.entry("19", colorizeHex("#A8A878", "Rattata")),
            Map.entry("20", colorizeHex("#A8A878", "Raticate")),
            Map.entry("21", colorizeHex("#A8A878", "Spearow")),
            Map.entry("22", colorizeHex("#A8A878", "Fearow")),
            Map.entry("23", colorizeHex("#A040A0", "Ekans")),
            Map.entry("24", colorizeHex("#A040A0", "Arbok")),
            Map.entry("25", colorizeHex("#F8D030", "Pikachu")),
            Map.entry("26", colorizeHex("#F8D030", "Raichu")),
            Map.entry("27", colorizeHex("#E0C068", "Sandshrew")),
            Map.entry("28", colorizeHex("#E0C068", "Sandslash")),
            Map.entry("29", colorizeHex("#A040A0", "Nidoran♀")),
            Map.entry("30", colorizeHex("#A040A0", "Nidorina")),
            Map.entry("31", colorizeHex("#A040A0", "Nidoqueen")),
            Map.entry("32", colorizeHex("#A040A0", "Nidoran♂")),
            Map.entry("33", colorizeHex("#A040A0", "Nidorino")),
            Map.entry("34", colorizeHex("#A040A0", "Nidoking")),
            Map.entry("35", colorizeHex("#A8A878", "Clefairy")),
            Map.entry("36", colorizeHex("#A8A878", "Clefable")),
            Map.entry("37", colorizeHex("#EE8130", "Vulpix")),
            Map.entry("38", colorizeHex("#EE8130", "Ninetales")),
            Map.entry("39", colorizeHex("#A8A878", "Jigglypuff")),
            Map.entry("40", colorizeHex("#A8A878", "Wigglytuff")),
            Map.entry("41", colorizeHex("#A040A0", "Zubat")),
            Map.entry("42", colorizeHex("#A040A0", "Golbat")),
            Map.entry("43", colorizeHex("#78C850", "Oddish")),
            Map.entry("44", colorizeHex("#78C850", "Gloom")),
            Map.entry("45", colorizeHex("#78C850", "Vileplume")),
            Map.entry("46", colorizeHex("#A8B820", "Paras")),
            Map.entry("47", colorizeHex("#A8B820", "Parasect")),
            Map.entry("48", colorizeHex("#A8B820", "Venonat")),
            Map.entry("49", colorizeHex("#A8B820", "Venomoth")),
            Map.entry("50", colorizeHex("#E0C068", "Diglett")),
            Map.entry("51", colorizeHex("#E0C068", "Dugtrio")),
            Map.entry("52", colorizeHex("#A8A878", "Meowth")),
            Map.entry("53", colorizeHex("#A8A878", "Persian")),
            Map.entry("54", colorizeHex("#6890F0", "Psyduck")),
            Map.entry("55", colorizeHex("#6890F0", "Golduck")),
            Map.entry("56", colorizeHex("#C03028", "Mankey")),
            Map.entry("57", colorizeHex("#C03028", "Primeape")),
            Map.entry("58", colorizeHex("#EE8130", "Growlithe")),
            Map.entry("59", colorizeHex("#EE8130", "Arcanine")),
            Map.entry("60", colorizeHex("#6890F0", "Poliwag")),
            Map.entry("61", colorizeHex("#6890F0", "Poliwhirl")),
            Map.entry("62", colorizeHex("#6890F0", "Poliwrath")),
            Map.entry("63", colorizeHex("#F85888", "Abra")),
            Map.entry("64", colorizeHex("#F85888", "Kadabra")),
            Map.entry("65", colorizeHex("#F85888", "Alakazam")),
            Map.entry("66", colorizeHex("#C03028", "Machop")),
            Map.entry("67", colorizeHex("#C03028", "Machoke")),
            Map.entry("68", colorizeHex("#C03028", "Machamp")),
            Map.entry("69", colorizeHex("#78C850", "Bellsprout")),
            Map.entry("70", colorizeHex("#78C850", "Weepinbell")),
            Map.entry("71", colorizeHex("#78C850", "Victreebel")),
            Map.entry("72", colorizeHex("#6890F0", "Tentacool")),
            Map.entry("73", colorizeHex("#6890F0", "Tentacruel")),
            Map.entry("74", colorizeHex("#B8A038", "Geodude")),
            Map.entry("75", colorizeHex("#B8A038", "Graveler")),
            Map.entry("76", colorizeHex("#B8A038", "Golem")),
            Map.entry("77", colorizeHex("#EE8130", "Ponyta")),
            Map.entry("78", colorizeHex("#EE8130", "Rapidash")),
            Map.entry("79", colorizeHex("#6890F0", "Slowpoke")),
            Map.entry("80", colorizeHex("#6890F0", "Slowbro")),
            Map.entry("81", colorizeHex("#F8D030", "Magnemite")),
            Map.entry("82", colorizeHex("#F8D030", "Magneton")),
            Map.entry("83", colorizeHex("#A8A878", "Farfetch'd")),
            Map.entry("84", colorizeHex("#A8A878", "Doduo")),
            Map.entry("85", colorizeHex("#A8A878", "Dodrio")),
            Map.entry("86", colorizeHex("#6890F0", "Seel")),
            Map.entry("87", colorizeHex("#98D8D8", "Dewgong")),
            Map.entry("88", colorizeHex("#A040A0", "Grimer")),
            Map.entry("89", colorizeHex("#A040A0", "Muk")),
            Map.entry("90", colorizeHex("#6890F0", "Shellder")),
            Map.entry("91", colorizeHex("#98D8D8", "Cloyster")),
            Map.entry("92", colorizeHex("#705898", "Gastly")),
            Map.entry("93", colorizeHex("#705898", "Haunter")),
            Map.entry("94", colorizeHex("#705898", "Gengar")),
            Map.entry("95", colorizeHex("#B8A038", "Onix")),
            Map.entry("96", colorizeHex("#F85888", "Drowzee")),
            Map.entry("97", colorizeHex("#F85888", "Hypno")),
            Map.entry("98", colorizeHex("#6890F0", "Krabby")),
            Map.entry("99", colorizeHex("#6890F0", "Kingler")),
            Map.entry("100", colorizeHex("#F8D030", "Voltorb")),
            Map.entry("101", colorizeHex("#F8D030", "Electrode")),
            Map.entry("102", colorizeHex("#78C850", "Exeggcute")),
            Map.entry("103", colorizeHex("#78C850", "Exeggutor")),
            Map.entry("104", colorizeHex("#E0C068", "Cubone")),
            Map.entry("105", colorizeHex("#E0C068", "Marowak")),
            Map.entry("106", colorizeHex("#C03028", "Hitmonlee")),
            Map.entry("107", colorizeHex("#C03028", "Hitmonchan")),
            Map.entry("108", colorizeHex("#A8A878", "Lickitung")),
            Map.entry("109", colorizeHex("#A040A0", "Koffing")),
            Map.entry("110", colorizeHex("#A040A0", "Weezing")),
            Map.entry("111", colorizeHex("#E0C068", "Rhyhorn")),
            Map.entry("112", colorizeHex("#E0C068", "Rhydon")),
            Map.entry("113", colorizeHex("#A8A878", "Chansey")),
            Map.entry("114", colorizeHex("#78C850", "Tangela")),
            Map.entry("115", colorizeHex("#A8A878", "Kangaskhan")),
            Map.entry("116", colorizeHex("#6890F0", "Horsea")),
            Map.entry("117", colorizeHex("#6890F0", "Seadra")),
            Map.entry("118", colorizeHex("#6890F0", "Goldeen")),
            Map.entry("119", colorizeHex("#6890F0", "Seaking")),
            Map.entry("120", colorizeHex("#6890F0", "Staryu")),
            Map.entry("121", colorizeHex("#6890F0", "Starmie")),
            Map.entry("122", colorizeHex("#F85888", "Mr. Mime")),
            Map.entry("123", colorizeHex("#A8B820", "Scyther")),
            Map.entry("124", colorizeHex("#98D8D8", "Jynx")),
            Map.entry("125", colorizeHex("#F8D030", "Electabuzz")),
            Map.entry("126", colorizeHex("#EE8130", "Magmar")),
            Map.entry("127", colorizeHex("#A8B820", "Pinsir")),
            Map.entry("128", colorizeHex("#A8A878", "Tauros")),
            Map.entry("129", colorizeHex("#6890F0", "Magikarp")),
            Map.entry("130", colorizeHex("#6890F0", "Gyarados")),
            Map.entry("131", colorizeHex("#6890F0", "Lapras")),
            Map.entry("132", colorizeHex("#A8A878", "Ditto")),
            Map.entry("133", colorizeHex("#A8A878", "Eevee")),
            Map.entry("134", colorizeHex("#6890F0", "Vaporeon")),
            Map.entry("135", colorizeHex("#F8D030", "Jolteon")),
            Map.entry("136", colorizeHex("#EE8130", "Flareon")),
            Map.entry("137", colorizeHex("#A8A878", "Porygon")),
            Map.entry("138", colorizeHex("#B8A038", "Omanyte")),
            Map.entry("139", colorizeHex("#B8A038", "Omastar")),
            Map.entry("140", colorizeHex("#B8A038", "Kabuto")),
            Map.entry("141", colorizeHex("#B8A038", "Kabutops")),
            Map.entry("142", colorizeHex("#B8A038", "Aerodactyl")),
            Map.entry("143", colorizeHex("#A8A878", "Snorlax")),
            Map.entry("144", colorizeHex("#98D8D8", "Articuno")),
            Map.entry("145", colorizeHex("#F8D030", "Zapdos")),
            Map.entry("146", colorizeHex("#EE8130", "Moltres")),
            Map.entry("147", colorizeHex("#7038F8", "Dratini")),
            Map.entry("148", colorizeHex("#7038F8", "Dragonair")),
            Map.entry("149", colorizeHex("#7038F8", "Dragonite")),
            Map.entry("150", colorizeHex("#F85888", "Mewtwo")),
            Map.entry("151", colorizeHex("#F85888", "Mew"))
    );
    public StartupActions(DemoService demoService, LogDecorator deco) {
        this.demoService = demoService;
        this.deco = deco;
    }

    @EventListener(ApplicationReadyEvent.class)
    @WithSpan("MySpan")
    @Logged
    public void onReady() {

        log.info("MDC MAP = {}", org.slf4j.MDC.getCopyOfContextMap());
        log.warn(deco.red("------- Scott Starting --------"));
        log.info(deco.pink(demoService.salute()));
        demoService.sum(2, 3);
        demoService.divide(4500, 25);
        try {
            demoService.explode();
        } catch (Exception e) {
            // ignore
        }

        demoService.IAmInvisible(kanto151);

        log.info(deco.green("------- Scott Finished Startup Actions --------"));
    }
}
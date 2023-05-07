package com.example.partnervermittlung.service;

import com.example.partnervermittlung.model.Geschlecht;
import com.example.partnervermittlung.model.Profil;
import com.example.partnervermittlung.service.Partnervermittlung;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PartnervermittlungTest {

    private Partnervermittlung partnervermittlung;

    @BeforeEach
    public void setUp() {
        partnervermittlung = new Partnervermittlung();
    }

    @Test
    public void testProfilEintragen() {
        Profil profil = new Profil(UUID.randomUUID(), "Max Mustermann", LocalDate.now(), Geschlecht.MAENNLICH,
                "Interessen", "Wohnort", Geschlecht.WEIBLICH, 18, 99, "Suchinteressen", "Suchwohnort");

        partnervermittlung.profilEintragen(profil);

        ObservableList<Profil> profile = partnervermittlung.getProfile();
        assertTrue(profile.contains(profil));
    }

    @Test
    public void testProfilSuchen() {
        UUID uuid = UUID.randomUUID();
        Profil profil = new Profil(uuid, "Max Mustermann", LocalDate.now(), Geschlecht.MAENNLICH,
                "Interessen", "Wohnort", Geschlecht.WEIBLICH, 18, 99, "Suchinteressen", "Suchwohnort");
        partnervermittlung.profilEintragen(profil);

        Profil gefundenesProfil = partnervermittlung.profilSuchen(uuid);

        assertEquals(profil, gefundenesProfil);
    }

    @Test
    public void testFilterProfiles() {
        Profil profil1 = new Profil(UUID.randomUUID(), "Max Mustermann", LocalDate.now(), Geschlecht.MAENNLICH,
                "Interessen", "Wohnort1", Geschlecht.WEIBLICH, 18, 99, "Suchinteressen", "Suchwohnort");
        Profil profil2 = new Profil(UUID.randomUUID(), "Erika Musterfrau", LocalDate.now(), Geschlecht.WEIBLICH,
                "Interessen", "Wohnort2", Geschlecht.MAENNLICH, 18, 99, "Suchinteressen", "Suchwohnort");

        partnervermittlung.profilEintragen(profil1);
        partnervermittlung.profilEintragen(profil2);

        List<Profil> filteredProfiles = partnervermittlung.filterProfiles("Wohnort1", Geschlecht.MAENNLICH);

        assertEquals(1, filteredProfiles.size());
        assertTrue(filteredProfiles.contains(profil1));
    }

    @Test
    public void testProfilLoeschen() {
        UUID uuid = UUID.randomUUID();
        Profil profil = new Profil(uuid, "Max Mustermann", LocalDate.now(), Geschlecht.MAENNLICH,
                "Interessen", "Wohnort", Geschlecht.WEIBLICH, 18, 99, "Suchinteressen", "Suchwohnort");
        partnervermittlung.profilEintragen(profil);

        assertTrue(partnervermittlung.profilLoeschen(uuid));
        assertFalse(partnervermittlung.getProfile().contains(profil));
    }
    @Test
    public void testProfileSpeichern() {
        Profil profil = new Profil(UUID.randomUUID(), "Max Mustermann", LocalDate.now(), Geschlecht.MAENNLICH,
                "Interessen", "Wohnort", Geschlecht.WEIBLICH, 18, 99, "Suchinteressen", "Suchwohnort");

        partnervermittlung.profilEintragen(profil);

        assertTrue(partnervermittlung.profileSpeichern());
    }

    @Test
    public void testProfileLaden() {
        Profil profil = new Profil(UUID.randomUUID(), "Max Mustermann", LocalDate.now(), Geschlecht.MAENNLICH,
                "Interessen", "Wohnort", Geschlecht.WEIBLICH, 18, 99,
                "Suchinteressen", "Suchwohnort");

        partnervermittlung.profilEintragen(profil);
        assertTrue(partnervermittlung.profileSpeichern());
        partnervermittlung.alleProfileLoeschen();
        partnervermittlung.profileLaden();


       // partnervermittlung = new Partnervermittlung(); // Neues Objekt erstellen, um Profile zu laden

        assertEquals(true, partnervermittlung.getProfile().contains(profil));
    }

    @Test
    public void testProfilLoeschenPerNameUndGeburtsdatum() {
        partnervermittlung.alleProfileLoeschen();
        UUID uuid = UUID.randomUUID();
        Profil profil = new Profil(uuid, "Max Mustermann", LocalDate.now(), Geschlecht.MAENNLICH,
                "Interessen", "Wohnort", Geschlecht.WEIBLICH, 18, 99, "Suchinteressen", "Suchwohnort");
        partnervermittlung.profilEintragen(profil);
        assertTrue(partnervermittlung.profilLoeschenPerNameUndGeburtsdatum("Max Mustermann", LocalDate.now()));
        assertFalse(partnervermittlung.getProfile().contains(profil));
    }
    @Test
    public void testGibProfileAlsString() {
        partnervermittlung.alleProfileLoeschen();
        Profil profil1 = new Profil(UUID.randomUUID(), "Max Mustermann", LocalDate.now(), Geschlecht.MAENNLICH,
                "Interessen", "Wohnort1", Geschlecht.WEIBLICH, 18, 99, "Suchinteressen", "Suchwohnort");
        Profil profil2 = new Profil(UUID.randomUUID(), "Erika Musterfrau", LocalDate.now(), Geschlecht.WEIBLICH,
                "Interessen", "Wohnort2", Geschlecht.MAENNLICH, 18, 99, "Suchinteressen", "Suchwohnort");

        partnervermittlung.profilEintragen(profil1);
        partnervermittlung.profilEintragen(profil2);

        String expected = "1: \n" + profil1 + "\n2: \n" + profil2 + "\n";

        assertEquals(expected, partnervermittlung.gibProfileAlsString());
    }


}
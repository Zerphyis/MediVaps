package dev.Zerphyis.medVaps.Entity.Records.Auth;

import dev.Zerphyis.medVaps.Entity.Login.TypeRole;

public record AutenticationData(String email, String password, TypeRole role) {
}

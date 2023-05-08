package com.spotlight.platform.userprofile.api.core.profile.persistence;

import com.spotlight.platform.userprofile.api.core.exceptions.persistance.InvalidUserProfileException;
import com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfileFixtures;

import org.junit.jupiter.api.Test;

import static com.spotlight.platform.userprofile.api.model.profile.primitives.UserProfileFixtures.USER_PROFILE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

class UserProfileDaoInMemoryTest {

    private final UserProfileDao dao = new UserProfileDaoInMemory();

    @Test
    void getNonExistingUser_OptionalEmptyReturned() {
        assertThat(dao.get(UserProfileFixtures.NON_EXISTING_USER_ID)).isEmpty();
    }

    @Test
    void getExistingUser_OptionalEmptyReturned() {
        dao.addUpdate(UserProfileFixtures.USER_PROFILE);

        assertThat(dao.get(UserProfileFixtures.USER_ID)).usingRecursiveComparison()
        .isEqualTo(Optional.of(UserProfileFixtures.USER_PROFILE));
    }

    @Test
    void addUpdateAndGetUser_ReturnsCorrectValues() {
        dao.addUpdate(UserProfileFixtures.USER_PROFILE);

        assertThat(dao.get(UserProfileFixtures.USER_ID)).hasValueSatisfying(
                userProfile -> assertThat(userProfile).usingRecursiveComparison().isEqualTo(USER_PROFILE));
    }

    @Test
    void addUpdateNoProfile_throwsException() {
        assertThatThrownBy(()-> dao.addUpdate(null)).isExactlyInstanceOf(
            InvalidUserProfileException.class);
    }
}
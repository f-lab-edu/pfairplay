package api.member;

import api.dto.member.LoginDto;
import api.dto.member.SignInDto;
import api.dto.member.UpdateMemberDto;
import api.util.JsonConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MemberControllerIntegrationTest {

    private final MockMvc mockMvc;

    @Autowired
    public MemberControllerIntegrationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void succeedToSignIn() throws Exception {
        // given
        SignInDto signInDto = new SignInDto(
                "testName",
                "testNickName",
                "2024-01-01",
                "010-1234-5678",
                null,
                null
        );

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/members/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConverter.toJsonString(signInDto)));

        // then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.phoneNumber").value("010-1234-5678")
                );


    }

    @Test
    public void failedToSignInByDuplicatedPhoneNumber() throws Exception {
        // given
        SignInDto signInDto = new SignInDto(
                "testName",
                "testNickName",
                "2024-01-01",
                "010-1234-5678",
                null,
                null
        );

        SignInDto duplicatedPhoneNumberSignInDto = new SignInDto(
                "testName",
                "testNickName",
                "2024-01-01",
                "010-1234-5678",
                null,
                null
        );

        // when
        ResultActions succeedResultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/members/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConverter.toJsonString(signInDto)));

        ResultActions failResultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/members/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConverter.toJsonString(duplicatedPhoneNumberSignInDto)));

        // then
        succeedResultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.phoneNumber").value("010-1234-5678")
                );

        failResultActions
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CONFLICT.value()));

    }

    @Test
    public void succeedToSignOut() throws Exception {
        // given
        SignInDto signInDto = new SignInDto(
                "testName",
                "testNickName",
                "2024-01-01",
                "010-1234-5678",
                null,
                null
        );

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/members/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.toJsonString(signInDto)))
                .andReturn();

        String savedMemberId = JsonConverter.toJsonNode(mvcResult.getResponse().getContentAsString())
                .get("id")
                .asText();

        ResultActions signOutResultActions = mockMvc.perform(MockMvcRequestBuilders
                .post(String.format("/api/members/%s/sign-out", savedMemberId))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        signOutResultActions
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void failedToSignOutBySourceNotFound() throws Exception {
        // given
        String nonExistedId = "nonExistedId";

        // when
        ResultActions signOutResultActions = mockMvc.perform(MockMvcRequestBuilders
                .post(String.format("/api/members/%s/sign-out", nonExistedId))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        signOutResultActions
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));

    }

    @Test
    public void succeedToLogIn() throws Exception {
        // given
        SignInDto signInDto = new SignInDto(
                "testName",
                "testNickName",
                "2024-01-01",
                "010-1234-5678",
                null,
                null
        );

        LoginDto loginDto = new LoginDto("010-1234-5678");

        // when
        ResultActions signInResultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/members/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConverter.toJsonString(signInDto)));

        ResultActions logInResultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/members/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConverter.toJsonString(loginDto)));

        // then
        logInResultActions
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void failedToLogInBySourceNotFound() throws Exception {
        // given
        LoginDto loginDto = new LoginDto("nonExistedPhoneNumber");

        // when
        ResultActions logInResultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/members/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConverter.toJsonString(loginDto)));

        // then
        logInResultActions
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));

    }

    @Test
    public void succeedToLogout() throws Exception {
        // given
        SignInDto signInDto = new SignInDto(
                "testName",
                "testNickName",
                "2024-01-01",
                "010-1234-5678",
                null,
                null
        );

        LoginDto loginDto = new LoginDto("010-1234-5678");

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/members/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.toJsonString(signInDto)))
                .andReturn();

        String savedMemberId = JsonConverter.toJsonNode(mvcResult.getResponse().getContentAsString())
                .get("id")
                .asText();

        ResultActions logoutResultActions = mockMvc.perform(MockMvcRequestBuilders
                .post(String.format("/api/members/%s/logout", savedMemberId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConverter.toJsonString(loginDto)));

        // then
        logoutResultActions
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void failedToLogoutBySourceNotFound() throws Exception {
        // given
        String nonExistedId = "nonExistedId";

        // when
        ResultActions logInResultActions = mockMvc.perform(MockMvcRequestBuilders
                .post(String.format("/api/members/%s/logout", nonExistedId))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        logInResultActions
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));

    }

    @Test
    public void succeedToGetMember() throws Exception {
        // given
        SignInDto signInDto = new SignInDto(
                "testName",
                "testNickName",
                "2024-01-01",
                "010-1234-5678",
                null,
                null
        );

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/members/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.toJsonString(signInDto)))
                .andReturn();

        String savedMemberId = JsonConverter.toJsonNode(mvcResult.getResponse().getContentAsString())
                .get("id")
                .asText();

        ResultActions getMemberResultActions = mockMvc.perform(MockMvcRequestBuilders
                .get(String.format("/api/members/%s", savedMemberId))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        getMemberResultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedMemberId));

    }

    @Test
    public void failedToGetMember() throws Exception {
        // given
        String nonExistedId = "nonExistedId";

        // when
        ResultActions logInResultActions = mockMvc.perform(MockMvcRequestBuilders
                .get(String.format("/api/members/%s", nonExistedId))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        logInResultActions
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));

    }

    @Test
    public void succeedToUpdateMember() throws Exception {
        // given
        SignInDto signInDto = new SignInDto(
                "testName",
                "testNickName",
                "2024-01-01",
                "010-1234-5678",
                null,
                null
        );

        UpdateMemberDto updateMemberDto = new UpdateMemberDto(
                "updatedNickname",
                "010-1111-2222",
                new ArrayList<>()
        );

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/members/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.toJsonString(signInDto)))
                .andReturn();

        String savedMemberId = JsonConverter.toJsonNode(mvcResult.getResponse().getContentAsString())
                .get("id")
                .asText();

        ResultActions updateMemberResultActions = mockMvc.perform(MockMvcRequestBuilders
                .put(String.format("/api/members/%s", savedMemberId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConverter.toJsonString(updateMemberDto)));

        // then
        updateMemberResultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nickname").value(updateMemberDto.getNickname()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(updateMemberDto.getPhoneNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.preferredPositions").value(updateMemberDto.getNickname()));
        // TODO expect preferredPositions converting

    }

    @Test
    public void failedToUpdateMember() throws Exception {
        // given
        String nonExistedId = "nonExistedId";

        UpdateMemberDto updateMemberDto = new UpdateMemberDto(
                "updatedNickname",
                "010-1111-2222",
                null
        );

        // when
        ResultActions logInResultActions = mockMvc.perform(MockMvcRequestBuilders
                .put(String.format("/api/members/%s", nonExistedId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonConverter.toJsonString(updateMemberDto)));

        // then
        logInResultActions
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));

    }

}

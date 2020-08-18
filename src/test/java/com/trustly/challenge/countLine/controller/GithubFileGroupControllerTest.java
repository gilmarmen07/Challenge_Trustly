package com.trustly.challenge.countLine.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trustly.challenge.countLine.model.GithubFile;
import com.trustly.challenge.countLine.services.GithubFileGroupService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = GithubFileGroupController.class)
public class GithubFileGroupControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GithubFileGroupService githubElementService;

	private String requestFindUri = "/githubFileGroups/find?user=gilmarmen07&name=Challenge_Trustly";
	private String requestLoadUri = "/githubFileGroups/load?user=gilmarmen07&name=Challenge_Trustly";
	private String requestUri = "/githubFileGroups";

	private String user = "gilmarmen07";
	private String name = "Challenge_Trustly";
	private List<GithubFile> githubFileList = new ArrayList<GithubFile>();

	private GithubFile githubFile;

	@Before
	public void setup() throws Exception {
		String description = null;
		String extension = null;
		for (Long c = (long) 13; c >= 0; c--) {
			githubFile = new GithubFile("gilmarmen07", "Challenge_Trustly");
			if (c % 2 == 0) {
				extension = ".txt";
				description = "FileTest" + c + extension;
			} else {
				extension = ".java";
				description = "FileTest" + c + extension;
			}
			githubFile.setDescription(description);
			githubFile.setExtension(extension);
			githubFile.setSize(new Long(1024));
			githubFile.setQuantLines(new Long(50));
			githubFileList.add(githubFile);
		}
	}

	@Test
	public void loadAllGithubFileList() throws Exception {
		Mockito.when(githubElementService.findAll()).thenReturn(githubFileList);
		String response = this.asJsonString(githubFileList);

		this.mockMvc
				.perform(get(requestUri).contentType(MediaType.APPLICATION_JSON).content(this.asJsonString(response)))
				.andDo(print()).andExpect(MockMvcResultMatchers.status().isOk());

		Mockito.verify(githubElementService).findAll();
	}

	@Test
	public void findGithubUserGroupList() throws Exception {
		Mockito.when(githubElementService.findByRepository(user, name)).thenReturn(githubFileList);
		String response = this.asJsonString(githubFileList);

		this.mockMvc
				.perform(get(requestFindUri).contentType(MediaType.APPLICATION_JSON)
						.content(this.asJsonString(response)))
				.andDo(print()).andExpect(MockMvcResultMatchers.status().isOk());

		Mockito.verify(githubElementService).findByRepository(user, name);

	}

	@Test
	public void loadGithubUserGroupList() throws Exception {
		Mockito.when(githubElementService.loadFromRepository(user, name)).thenReturn(githubFileList);
		String response = this.asJsonString(githubFileList);

		this.mockMvc
				.perform(get(requestLoadUri).contentType(MediaType.APPLICATION_JSON)
						.content(this.asJsonString(response)))
				.andDo(print()).andExpect(MockMvcResultMatchers.status().isOk());

		Mockito.verify(githubElementService).loadFromRepository(user, name);

	}

	public String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

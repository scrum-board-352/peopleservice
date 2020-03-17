package com.microservice.peopleservice.resource

import com.microservice.peopleservice.dto.Message
import com.microservice.peopleservice.entity.Team
import com.microservice.peopleservice.service.MailService
import com.microservice.peopleservice.service.TeamService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TeamResource {
    @Autowired
    private lateinit var teamService: TeamService
    @Autowired
    private lateinit var mailService: MailService

    @PostMapping("/team/create")
    fun createTeam(@RequestParam teamname: String,
                   @RequestParam description: String,
                   @RequestParam creator: String): Message {
        val newTeam = Team(creator, teamname, description)

        return teamService.createTeam(newTeam)
    }

    @GetMapping("/team/handleUser")
    fun modifyUserInTeam(@RequestParam teamId: Int,
                         @RequestParam username: String,
                         @RequestParam isAdd: Boolean): Message {

        return teamService.modifyUserWithTeamHandler(teamId, username, isAdd)
    }

    @PostMapping("/team/update")
    fun updateTeam(@RequestParam id: Int,
                   @RequestParam creator: String,
                   @RequestParam teamname: String,
                   @RequestParam description: String): Message {
        val updateTeam = Team(creator, teamname, description)
        updateTeam.id = id

        return teamService.updateTeamById(updateTeam)
    }

    @PostMapping("/team/remove")
    fun removeTeam(@RequestParam id: Int): Message {
        return teamService.removeTeamById(id)
    }

    @PostMapping("/mail/send")
    fun sendEmail(@RequestParam receiverMail: String,
                  @RequestParam announcer: String,
                  @RequestParam teamId: Int) {
        return mailService.sendEmailToInviteReceiver(receiverMail, announcer, teamId)
    }
}
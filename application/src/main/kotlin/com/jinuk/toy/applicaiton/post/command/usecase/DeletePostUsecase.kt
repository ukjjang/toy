package com.jinuk.toy.applicaiton.post.command.usecase

import com.jinuk.toy.domain.comment.service.CommentCommandService
import com.jinuk.toy.domain.post.service.PostCommandService
import org.springframework.stereotype.Service

@Service
class DeletePostUsecase(
    private val postCommandService: PostCommandService,
    private val commentCommandService: CommentCommandService,
) {
    operator fun invoke(command: DeletePostCommand) =
        with(command) {
            postCommandService.delete(id, userId)
            commentCommandService.deleteByPostId(id)
        }
}

data class DeletePostCommand(val userId: Long, val id: Long)

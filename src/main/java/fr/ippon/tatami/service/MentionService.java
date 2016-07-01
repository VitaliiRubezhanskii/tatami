package fr.ippon.tatami.service;

import fr.ippon.tatami.domain.User;
import fr.ippon.tatami.domain.status.Status;
import fr.ippon.tatami.repository.MentionlineRepository;
import fr.ippon.tatami.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Notifies a user when he is mentionned.
 */
@Service
public class MentionService {

    @Inject
    private MentionlineRepository mentionlineRepository;

    @Inject
    private MailService mailService;

//    @Autowired(required = false)
//    private ApplePushService applePushService;

    @Inject
    private UserRepository userRepository;

    /**
     * A status that mentions a user is put in the user's mentionline and in his timeline.
     * The mentioned user can also be notified by email.
     */
    public void mentionUser(String mentionedEmail, Status status) {
        Optional<User> userOptional = userRepository.findOneByEmail(mentionedEmail);
        if(userOptional.isPresent()){
        mentionlineRepository.addStatusToMentionline(mentionedEmail, status.getStatusId().toString());
        User mentionnedUser = userOptional.get();

//        Send a push notification to the person mentionned.
//        if (mentionnedUser != null && (mentionnedUser.getMentionEmail() == null
//            || mentionnedUser.getMentionEmail().equals(true))) {
//            if (status.getStatusPrivate()) { // Private status
//                mailService.sendUserPrivateMessageEmail(mentionnedUser, status);
//                if (applePushService != null) {
//                    applePushService.notifyUser(mentionedUsername, status);
//                }
//            } else {
//                mailService.sendUserMentionEmail(mentionnedUser, status);
//                if (applePushService != null) {
//                    applePushService.notifyUser(mentionedUsername, status);
//                }
//            }
//        }
        }


    }
}

package uk.co.compendiumdev.challenge;

import uk.co.compendiumdev.thingifier.api.http.HttpApiRequest;
import uk.co.compendiumdev.thingifier.api.http.HttpApiResponse;
import uk.co.compendiumdev.thingifier.apiconfig.ThingifierApiConfig;
import uk.co.compendiumdev.thingifier.application.httpapimessagehooks.HttpApiRequestHook;

import static uk.co.compendiumdev.challenge.Challenges.CHALLENGE.GET_HEAD_TODOS;
import static uk.co.compendiumdev.challenge.Challenges.CHALLENGE.GET_TODOS;

public class ChallengerApiRequestHook implements HttpApiRequestHook {

    private final Challenges challenges;

    public ChallengerApiRequestHook(Challenges challenges){
        this.challenges = challenges;
    }
    @Override
    public HttpApiResponse run(final HttpApiRequest request, final ThingifierApiConfig config) {

        if(request.getVerb() == HttpApiRequest.VERB.GET &&
            request.getPath().contentEquals("todos") &&
            request.getQueryParams().size()==0){
            challenges.pass(GET_TODOS);
        }

        if(request.getVerb() == HttpApiRequest.VERB.HEAD &&
                request.getPath().contentEquals("todos") &&
                request.getQueryParams().size()==0){
            challenges.pass(GET_HEAD_TODOS);
        }

        return null;
    }
}
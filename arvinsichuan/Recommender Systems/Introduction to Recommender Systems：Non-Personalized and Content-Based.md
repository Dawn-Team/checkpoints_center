# Introduciton to Recommender Systems: Non-personalized and Content-based.

## Vocabularies
- Rating(Expresion of preference)
    -   Explicit rating (direct from user)
    -   Implicit rating (inferred from user activities)
- Prediction(Estimate of preference)
- Recommendation(Selected items for user)
- Content(Attributes, text, etc)
- Collaborative(Using data from other users)

## Recommendation Approches
- Non-personalized and stereotyed
    - Popularity, Group, Preference
- Product Association
    - People who liked/bought `x`, also like `y`
- Content-based
    - Learn what I like in terms of attrs.
- Collaborative
    - Learn what I like, use others' experiences to recommend.
    
## Preference to collect
- Explicit
    - Rating
    - Review
    - Vote
- Implicit
    - Click
    - Purchase
    - Follow

Collecting scale and type of scale can be pretty useful.

when are rating provided?
- Consumption, just experienced
- Memory, experienced for a time.
- Expectation, never experienced before.

Prediction -> Scaled, Rated  
- Pro: helps quentify items.
- Con: provide something falsifiable.
Recommendation -> Top-n list
- Pro: Provides good choices as a default
- Con: If percieved as top-n, it can result in failure to explore.



## Analytical Framework
- Domain: what to recommend
- Purpose: Sale, inf, education?
- Context: Shopping, Listening music? how context constraint recommender
- Whose Opinion?
- Personalization Lever
- Privacy & Trustworthiness
- Interface: Prediction, Recommendation, Implicit or Expicit?
- Algorithm

## Non-personalized?
- New user
- Simple but beneficial
- Common displays
- Where Personalization if IMPOSSIBLE

## Factors that to pred/rec/eval
- Popularity if an important metric
- Average can be misleading(adjust by people, adjust by normalization, adjust by history rating)
- More data is better.


## Summary 
- Non-personalized popularity statics or average can be effective in the right application
- In many cases it can be best to show count, avarage, and distribution together,
- For ranking, one alternative to average is the percentage who score above a threshold.
- Personalization would solve many limitations.
* start docker containers with `docker-compose up`
* run gradle with `./gradlew build`

The `SyncCommitSpec` should be green. 
The idea is that because of the raised exception for `faultyMessage` and the `OffsetStrategy.SYNC` the offsets do not get committed.
Right now, the `initialOffset` still gets incremented, as the offset of `message` is committed.

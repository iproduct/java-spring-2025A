package course.spring.dao;

public interface RepoFactory {
    UserRepository createUserRepo(IdGenerator idGenerator);
}

package utils.interfaces.objectConverter;


public class AuditConverter implements ObjectConverter<AuditConverter> {
    @Override
    public AuditConverter convertObject(){
        return new AuditConverter();
    }
}

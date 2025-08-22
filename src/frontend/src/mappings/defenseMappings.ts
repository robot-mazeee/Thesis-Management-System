export const getColor = (status: string) => {
    switch(status) {
        case "IN_REVISION":
            return 'purple';
        case "DEFENSE_SCHEDULED":
            return 'yellow';
        case "SUBMITTED_TO_FENIX":
            return 'green';
        default:
            return 'red';
    }
}
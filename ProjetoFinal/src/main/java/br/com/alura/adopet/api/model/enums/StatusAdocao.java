package br.com.alura.adopet.api.model.enums;

/**
 * Enum que representa os possíveis status de uma adoção no sistema Adopet.
 *
 * <p>Esta enumeração define os status utilizados para o acompanhamento do processo de adoção de um pet.</p>
 *
 * <p>Os status de adoção disponíveis são:</p>
 * <ul>
 *     <li><strong>AGUARDANDO_AVALIACAO</strong>: A adoção está aguardando avaliação.</li>
 *     <li><strong>APROVADO</strong>: A adoção foi aprovada.</li>
 *     <li><strong>REPROVADO</strong>: A adoção foi reprovada.</li>
 * </ul>
 */
public enum StatusAdocao {

    /**
     * Representa o status onde a adoção está aguardando avaliação.
     */
    AGUARDANDO_AVALIACAO,

    /**
     * Representa o status onde a adoção foi aprovada.
     */
    APROVADO,

    /**
     * Representa o status onde a adoção foi reprovada.
     */
    REPROVADO;
}

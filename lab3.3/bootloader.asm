[BITS 16]
[ORG 0x7C00]

start:
    mov ah, 0x0E
    mov si, msg

print_string:
    lodsb
    cmp al,0
    je done
    int 0x10
    jmp print_string

done:
    jmp $

msg db 'Simple Bootloader is running!', 0

times 510-($-$$) db 0
dw 0xAA55
